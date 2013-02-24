package org.performance.web.service.release;

import java.util.List;

import org.performance.web.dao.model.release.Item;
import org.performance.web.dao.model.release.Owner;
import org.performance.web.dao.model.release.ReleaseChecklist;
import org.performance.web.dao.model.release.ReleaseChecklistSnapshot;
import org.performance.web.dao.model.release.ReleaseItem;
import org.performance.web.dao.model.release.ReleaseItemBean;

public interface ReleaseService {

	public boolean addOwner(Owner owner);

	public boolean updateOwner(Owner owner);

	public Owner getOwner(long id);

	public List<Owner> getAllOwners(boolean includedDeleted);

	public boolean deleteOwner(long id);

	public boolean logicDeleteOwner(Owner owner);

	public boolean addItem(Item item);

	public boolean updateItem(Item item);

	public Item getItem(long id);

	public List<Item> getAllItems(boolean includedDeleted);

	public List<Item> getItemsByCategory(String category);

	public boolean deleteItem(long id);
	public boolean moveItemUp(long id);
	public boolean moveItemDown(long id);

	public boolean logicDeleteItem(Item item);

	public boolean addRelease(ReleaseChecklist release);

	public boolean updateRelease(ReleaseChecklist release);

	public boolean setCurrentRelease(long id);

	public ReleaseChecklist getRelease(long id);

	public List<ReleaseChecklist> getAllReleases(boolean includedDeleted);

	public boolean deleteRelease(long id);

	public boolean logicDeleteRelease(ReleaseChecklist release);

	public boolean addReleaseItem(ReleaseItem releaseItem);

	public boolean updateReleaseItem(ReleaseItem releaseItem);

	public ReleaseItem getReleaseItem(long id);

	public List<ReleaseItem> getAllReleaseItems(boolean includedDeleted);

	public boolean deleteReleaseItem(long id);

	public ReleaseChecklist getCurrentRelease();

	public List<ReleaseItem> getCurrentReleaseItems();

	public boolean takeSnapshot();

	public List<ReleaseItem> getToDo();

	public List<ReleaseItem> getCurrentReleaseExcludeNA();

	public List<ReleaseItem> getNA();

	public List<ReleaseItem> getNextThreeDays();
	
	public List<ReleaseItem> getReleaseItemsByOwner(long ownerID);
	
	public ReleaseChecklistSnapshot getSnapshot(long releaseSnapshotID);
	
	public List<ReleaseChecklistSnapshot> getAllSnapshots();
	
	public int getCurrentMaxItemSequence();
	
	public boolean reOrderItemSequence();
	
	public boolean moveItemsSequenceDown(int startSequence);
	
}
