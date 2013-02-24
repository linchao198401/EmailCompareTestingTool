package org.performance.web.service.impl.release;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.sf.ezmorph.Morpher;
import net.sf.ezmorph.MorpherRegistry;
import net.sf.ezmorph.bean.BeanMorpher;
import net.sf.json.JSONArray;
import net.sf.json.util.JSONUtils;

import org.hibernate.Query;
import org.performance.web.common.GenericFilter;
import org.performance.web.dao.model.release.Item;
import org.performance.web.dao.model.release.Owner;
import org.performance.web.dao.model.release.ReleaseChecklist;
import org.performance.web.dao.model.release.ReleaseChecklistSnapshot;
import org.performance.web.dao.model.release.ReleaseItem;
import org.performance.web.dao.model.release.ReleaseItemBean;
import org.performance.web.dao.release.ItemDAO;
import org.performance.web.dao.release.OwnerDAO;
import org.performance.web.dao.release.ReleaseChecklistSnapshotDAO;
import org.performance.web.dao.release.ReleaseDAO;
import org.performance.web.dao.release.ReleaseItemDAO;
import org.performance.web.service.BaseService;
import org.performance.web.service.release.ReleaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { Throwable.class })
public class ReleaseServiceImpl extends BaseService implements ReleaseService {

	private OwnerDAO ownerDAO;
	private ItemDAO itemDAO;
	private ReleaseDAO releaseDAO;
	private ReleaseItemDAO releaseItemDAO;
	private ReleaseChecklistSnapshotDAO releaseChecklistSnapshotDAO;

	@Autowired
	public void setOwnerDAO(OwnerDAO ownerDAO) {
		this.ownerDAO = ownerDAO;
	}

	@Autowired
	public void setItemDAO(ItemDAO itemDAO) {
		this.itemDAO = itemDAO;
	}

	@Autowired
	public void setReleaseDAO(ReleaseDAO releaseDAO) {
		this.releaseDAO = releaseDAO;
	}

	@Autowired
	public void setReleaseItemDAO(ReleaseItemDAO releaseItemDAO) {
		this.releaseItemDAO = releaseItemDAO;
	}

	@Autowired
	public void setReleaseChecklistSnapshotDAO(
			ReleaseChecklistSnapshotDAO releaseChecklistSnapshotDAO) {
		this.releaseChecklistSnapshotDAO = releaseChecklistSnapshotDAO;
	}

	@Override
	public boolean addOwner(Owner owner) {
		ownerDAO.save(owner);
		return true;
	}

	@Override
	public boolean updateOwner(Owner owner) {
		ownerDAO.update(owner);
		return true;
	}

	@Override
	public Owner getOwner(long id) {
		return ownerDAO.findById(id);
	}

	@Override
	public List<Owner> getAllOwners(boolean includedDeleted) {
		return ownerDAO.find(
				"from Owner WHERE isDeleted = false ORDER BY name ASC", null);
	}

	@Override
	public boolean deleteOwner(long id) {
		ownerDAO.deleteById(id);
		return true;
	}

	@Override
	public boolean addItem(Item item) {
		int currentMaxItemSequence = getCurrentMaxItemSequence();
		if (item.getSequence() < currentMaxItemSequence) {
			moveItemsSequenceDown(item.getSequence());
		}
		itemDAO.save(item);
		if (item.getSequence() > currentMaxItemSequence) {
			reOrderItemSequence();
		}
		return true;
	}

	@Override
	public boolean updateItem(Item item) {
		itemDAO.update(item);
		return true;
	}

	@Override
	public Item getItem(long id) {
		return itemDAO.findById(id);
	}

	@Override
	public List<Item> getAllItems(boolean includedDeleted) {
		return itemDAO
				.find("from Item WHERE isDeleted = false ORDER BY sequence ASC",
						null);
	}

	@Override
	public boolean deleteItem(long id) {
		itemDAO.deleteById(id);
		return true;
	}

	@Override
	public boolean addRelease(ReleaseChecklist release) {
		loadItem(release);
		release.setCreatedDate(new Date());
		releaseDAO.save(release);
		return true;
	}

	private void loadItem(ReleaseChecklist release) {
		Set<ReleaseItem> releaseItems = release.getReleaseItems();
		Set<ReleaseItem> loadedReleaseItems = new HashSet<ReleaseItem>();
		if (releaseItems != null && releaseItems.size() > 0) {
			for (ReleaseItem releaseItem : releaseItems) {
				ReleaseItem loadedReleaseItem = new ReleaseItem();
				Item item = itemDAO.findById(releaseItem.getItem().getId());
				loadedReleaseItem.setItem(item);
				loadedReleaseItem.setRelease(release);
				loadedReleaseItems.add(loadedReleaseItem);
			}
		}
		release.setReleaseItems(loadedReleaseItems);
	}

	@Override
	public boolean updateRelease(ReleaseChecklist release) {
		ReleaseChecklist existingRelease = releaseDAO.findById(release.getId());
		lazyLoadAllItems(existingRelease);
		Set<ReleaseItem> existingReleaseItems = existingRelease
				.getReleaseItems();
		// release item use item id as key, so we can use the existing items to
		// replace the new item
		if (release.getReleaseItems() != null) {
			if (existingReleaseItems != null) {
				release.getReleaseItems().removeAll(existingReleaseItems);
				release.getReleaseItems().addAll(existingReleaseItems);
			}
		}
		release.setCurrentRelease(existingRelease.isCurrentRelease());
		release.setCreatedDate(existingRelease.getCreatedDate());
		releaseDAO.update(release);
		return true;
	}

	@Override
	public ReleaseChecklist getRelease(long id) {
		ReleaseChecklist release = releaseDAO.findById(id);
		lazyLoadAllItems(release);
		return release;
	}

	private void lazyLoadAllItems(ReleaseChecklist release) {
		for (ReleaseItem item : release.getReleaseItems()) {
			logger.info("" + item.getItem().getId());
		}
	}

	@Override
	public List<ReleaseChecklist> getAllReleases(boolean includedDeleted) {
		return releaseDAO
				.find("from ReleaseChecklist WHERE isDeleted = ? ORDER BY createdDate DESC",
						false);
	}

	@Override
	public boolean deleteRelease(long id) {
		releaseDAO.deleteById(id);
		return true;
	}

	@Override
	public boolean addReleaseItem(ReleaseItem releaseItem) {
		return false;
	}

	@Override
	public boolean updateReleaseItem(ReleaseItem releaseItem) {
		releaseItemDAO.update(releaseItem);
		return true;
	}

	@Override
	public ReleaseItem getReleaseItem(long id) {
		return releaseItemDAO.findById(id);
	}

	@Override
	public List<ReleaseItem> getAllReleaseItems(boolean includedDeleted) {
		List<GenericFilter> filters = GenericFilter.getSimpleGenericFilter(
				"isDeleted", includedDeleted);
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteReleaseItem(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean logicDeleteOwner(Owner owner) {
		owner.setDeleted(true);
		ownerDAO.update(owner);
		return true;
	}

	@Override
	public boolean logicDeleteItem(Item item) {
		item.setDeleted(true);
		itemDAO.update(item);
		return true;
	}

	@Override
	public boolean logicDeleteRelease(ReleaseChecklist release) {
		release.setDeleted(true);
		releaseDAO.update(release);
		return true;
	}

	@Override
	public List<Item> getItemsByCategory(String category) {
		HashMap<String, Object> paras = new HashMap<String, Object>();
		paras.put("isDeleted", false);
		paras.put("category", category);
		List<GenericFilter> filters = GenericFilter
				.getSimpleGenericFilter(paras);
		return itemDAO.findPageResult(filters).getResult();
	}

	@Override
	public boolean setCurrentRelease(long id) {
		String hql = "update ReleaseChecklist set isCurrentRelease = ?";
		releaseDAO.runHQL(hql, false);
		ReleaseChecklist release = releaseDAO.findById(id);
		release.setCurrentRelease(true);
		releaseDAO.update(release);
		return true;
	}

	@Override
	public ReleaseChecklist getCurrentRelease() {
		String hql = "from ReleaseChecklist r WHERE r.isCurrentRelease = ? AND isDeleted = false";
		List<ReleaseChecklist> result = releaseDAO.find(hql, true);
		ReleaseChecklist releaseChecklist = null;
		if (result != null && result.size() > 0) {
			releaseChecklist = result.get(0);
			Set<ReleaseItem> releaseItems = releaseChecklist.getReleaseItems();
			for (ReleaseItem item : releaseItems) {
				logger.debug(item.getItem().toString());
			}
		}
		return releaseChecklist;
	}

	@Override
	public List<ReleaseItem> getCurrentReleaseItems() {
		List<ReleaseItem> releaseItems = releaseItemDAO
				.find("from ReleaseItem  WHERE release.isCurrentRelease = true ORDER BY item.sequence",
						null);
		for (ReleaseItem item : releaseItems) {
			logger.debug(item.getItem().toString());
		}
		return releaseItems;
	}

	@Override
	public List<ReleaseItem> getToDo() {
		Date date = new Date();
		List<ReleaseItem> releaseItems = releaseItemDAO
				.find("from ReleaseItem  WHERE release.isCurrentRelease = true AND (dueDate = ? OR status = 'Delay') ORDER BY item.sequence",
						date);
		for (ReleaseItem item : releaseItems) {
			logger.debug(item.getItem().toString());
		}
		return releaseItems;
	}

	@Override
	public List<ReleaseItem> getReleaseItemsByOwner(long ownerID) {
		List<ReleaseItem> releaseItems = releaseItemDAO
				.find("from ReleaseItem  WHERE release.isCurrentRelease = true AND owner.id = ? ORDER BY item.sequence",
						ownerID);
		for (ReleaseItem item : releaseItems) {
			logger.debug(item.getItem().toString());
		}
		return releaseItems;
	}

	@Override
	public boolean moveItemUp(long id) {
		return moveItem(id, false);
	}

	private boolean moveItem(long id, boolean moveDown) {
		Item item = itemDAO.findById(id);
		int sequence = item.getSequence();
		String compare = "";
		if (moveDown) {
			compare = "> ? ORDER BY sequence ASC";
		} else {
			compare = "< ? ORDER BY sequence DESC";
		}
		String findNextSequence = "from Item WHERE isDeleted = false AND sequence " + compare;
		Query createQuery = itemDAO.createQuery(findNextSequence);
		createQuery.setParameter(0, sequence);
		createQuery.setMaxResults(1);
		List<Item> theOtherItems = createQuery.list();
		if (theOtherItems != null && theOtherItems.size() == 1) {
			long theOtherItemID = theOtherItems.get(0).getId();
			int nextSequence = theOtherItems.get(0).getSequence();
			itemDAO.runHQL("update Item set sequence = ? WHERE id = ?",
					sequence, theOtherItemID);
			itemDAO.runHQL("update Item set sequence = ? WHERE id = ?",
					nextSequence, id);
		}
		return true;

	}

	@Override
	public boolean moveItemDown(long id) {
		return moveItem(id, true);
	}

	@Override
	public List<ReleaseItem> getCurrentReleaseExcludeNA() {
		List<ReleaseItem> releaseItems = releaseItemDAO
				.find("from ReleaseItem  WHERE release.isCurrentRelease = true AND (status IS NULL OR status != 'N/A') ORDER BY item.sequence");
		for (ReleaseItem item : releaseItems) {
			logger.debug(item.getItem().toString());
		}
		return releaseItems;
	}

	@Override
	public List<ReleaseItem> getNA() {
		List<ReleaseItem> releaseItems = releaseItemDAO
				.find("from ReleaseItem  WHERE release.isCurrentRelease = true AND status = 'N/A' ORDER BY item.sequence");
		for (ReleaseItem item : releaseItems) {
			logger.debug(item.getItem().toString());
		}
		return releaseItems;
	}

	@Override
	public List<ReleaseItem> getNextThreeDays() {
		Date date = new Date();
		List<ReleaseItem> releaseItems = releaseItemDAO
				.find("from ReleaseItem  WHERE release.isCurrentRelease = true AND (dueDate < ?) ORDER BY item.sequence",
						date);
		for (ReleaseItem item : releaseItems) {
			logger.debug(item.getItem().toString());
		}
		return releaseItems;
	}

	@Override
	public boolean takeSnapshot() {
		Date now = new Date();
		ReleaseChecklist currentRelease = getCurrentRelease();
		ReleaseChecklistSnapshot snapshot = new ReleaseChecklistSnapshot();
		List<ReleaseItem> currentReleaseItems = getCurrentReleaseItems();
		ArrayList<ReleaseItemBean> allReleaseItemBeans = new ArrayList<ReleaseItemBean>();
		for (ReleaseItem releaseItem : currentReleaseItems) {
			String category = releaseItem.getItem().getCategory();
			String name = releaseItem.getItem().getName();
			Owner owner = releaseItem.getOwner();
			String ownerName = "";
			if (owner != null) {
				ownerName = owner.getName();
			}
			String status = releaseItem.getStatus();
			String description = releaseItem.getItem().getDescription();
			Date dueDate = releaseItem.getDueDate();
			String comments = releaseItem.getComments();
			ReleaseItemBean bean = new ReleaseItemBean();
			bean.setCategory(category);
			bean.setName(name);
			bean.setOwner(ownerName);
			bean.setStatus(status);
			bean.setDescription(description);
			bean.setDueDate(dueDate);
			bean.setComments(comments);
			allReleaseItemBeans.add(bean);
		}
		JSONArray value = JSONArray.fromObject(allReleaseItemBeans);
		snapshot.setValue(value.toString());
		snapshot.setCreatedDate(now);
		snapshot.setRelease(currentRelease);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss");
		snapshot.setName(currentRelease.getName() + " ("
				+ simpleDateFormat.format(now) + ")");
		snapshot.setType("Manually");
		releaseChecklistSnapshotDAO.save(snapshot);
		return true;
	}

	@Override
	public ReleaseChecklistSnapshot getSnapshot(long releaseSnapshotID) {
		ReleaseChecklistSnapshot snapshot = releaseChecklistSnapshotDAO
				.findById(releaseSnapshotID);
		String json = snapshot.getValue();

		JSONArray fromObject = JSONArray.fromObject(json);
		List list = JSONArray.toList(fromObject);
		MorpherRegistry morpherRegistry = JSONUtils.getMorpherRegistry();
		Morpher dynaMorpher = new BeanMorpher(ReleaseItemBean.class,
				JSONUtils.getMorpherRegistry());
		morpherRegistry.registerMorpher(dynaMorpher);
		dynaMorpher = new BeanMorpher(Date.class,
				JSONUtils.getMorpherRegistry());
		morpherRegistry.registerMorpher(dynaMorpher);
		List<ReleaseItemBean> releaseItems = new ArrayList<ReleaseItemBean>();
		for (Iterator i = list.iterator(); i.hasNext();) {
			releaseItems.add((ReleaseItemBean) morpherRegistry.morph(
					ReleaseItemBean.class, i.next()));
		}
		snapshot.setReleaseItems(releaseItems);

		return snapshot;
	}

	@Override
	public List<ReleaseChecklistSnapshot> getAllSnapshots() {
		return releaseChecklistSnapshotDAO
				.find("from ReleaseChecklistSnapshot WHERE release.isCurrentRelease = true ORDER BY id DESC");
	}

	@Override
	public int getCurrentMaxItemSequence() {
		Query query = itemDAO
				.createQuery("from Item ORDER BY sequence DESC");
		query.setMaxResults(1);
		List result = query.list();
		if (result != null && result.size() == 1) {
			return ((Item) result.get(0)).getSequence();
		}

		return 0;
	}

	@Override
	public boolean reOrderItemSequence() {
		String itemOrderBySequences = "from Item WHERE isDeleted = false ORDER BY sequence";
		List<Item> items = itemDAO.find(itemOrderBySequences);
		int sequence = 1;
		for (Item item : items) {
			item.setSequence(sequence);
			sequence++;
			itemDAO.update(item);
		}
		return true;
	}

	@Override
	public boolean moveItemsSequenceDown(int startSequence) {
		String moveDown = "update Item set sequence = sequence + 1 WHERE sequence >= " + startSequence;
		Query createQuery = itemDAO.createQuery(moveDown);
		createQuery.executeUpdate();
		return true;
	}
}
