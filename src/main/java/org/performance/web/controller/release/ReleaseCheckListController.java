package org.performance.web.controller.release;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.performance.web.dao.model.release.Item;
import org.performance.web.dao.model.release.Owner;
import org.performance.web.dao.model.release.ReleaseChecklist;
import org.performance.web.dao.model.release.ReleaseChecklistSnapshot;
import org.performance.web.dao.model.release.ReleaseItem;
import org.performance.web.service.release.ReleaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("releaseChecklist.do")
public class ReleaseCheckListController {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	private ReleaseService releaseService;

	private String backToOwnerHome = "redirect:releaseChecklist.do?op=listOwner";
	private String backToItemHome = "redirect:releaseChecklist.do?op=listItem";
	private String backToReleaseHome = "redirect:releaseChecklist.do?op=listRelease";

	@Autowired
	public void setService(ReleaseService releaseService) {
		this.releaseService = releaseService;
	}

	@RequestMapping(params = "op=listOwner")
	public String getOwnerList(Model model) {

		List<Owner> allOwners = releaseService.getAllOwners(false);
		model.addAttribute("allOwners", allOwners);
		return "release/ownerList";
	}

	@RequestMapping(params = "op=addOwner")
	public String addOwner(HttpSession session, @ModelAttribute Owner owner, Model model) {
		if (owner.getName() == null) {
			return "release/ownerAdd";
		}
		releaseService.addOwner(owner);
		return backToOwnerHome;
	}

	@RequestMapping(params = "op=updateOwner")
	public String updateOwner(HttpSession session, @ModelAttribute Owner owner, Model model) {
		releaseService.updateOwner(owner);
		return backToOwnerHome;
	}

	@RequestMapping(params = "op=deleteOwner")
	public String deleteOwner(@RequestParam("ownerID") long ownerID) {
		Owner owner = releaseService.getOwner(ownerID);
		if (owner != null) {
			releaseService.logicDeleteOwner(owner);
		}
		return backToOwnerHome;
	}

	@RequestMapping(params = "op=showOwner")
	public String showOwner(Model model, @RequestParam("ownerID") long ownerID) {
		Owner owner = releaseService.getOwner(ownerID);
		model.addAttribute("owner", owner);
		return "release/ownerShow";
	}

	@RequestMapping(params = "op=listItem")
	public String getItemList(Model model) {

		List<Item> allItems = releaseService.getAllItems(false);
		model.addAttribute("allItems", allItems);
		return "release/itemList";
	}

	@RequestMapping(params = "op=addItem")
	public String addItem(HttpSession session, @ModelAttribute Item item, Model model) {
		if (item.getName() == null) {
			return "release/itemAdd";
		}
		releaseService.addItem(item);
		return backToItemHome;
	}

	@RequestMapping(params = "op=updateItem")
	public String updateItem(HttpSession session, @ModelAttribute Item item, Model model) {
		releaseService.updateItem(item);
		return backToItemHome;
	}

	@RequestMapping(params = "op=deleteItem")
	public String deleteItem(@RequestParam("itemID") long itemID) {
		Item item = releaseService.getItem(itemID);
		if (item != null) {
			releaseService.logicDeleteItem(item);
		}
		return backToItemHome;
	}

	@RequestMapping(params = "op=moveItemUp")
	public String moveItemUp(@RequestParam("itemID") long itemID) {
		releaseService.moveItemUp(itemID);
		return "success";
	}

	@RequestMapping(params = "op=moveItemDown")
	public String moveItemDown(@RequestParam("itemID") long itemID) {
		releaseService.moveItemDown(itemID);
		return "success";
	}

	@RequestMapping(params = "op=showItem")
	public String showItem(Model model, @RequestParam("itemID") long itemID) {
		Item item = releaseService.getItem(itemID);
		model.addAttribute("item", item);
		return "release/itemShow";
	}

	@RequestMapping(params = "op=listRelease")
	public String listRelease(Model model) {
		List<ReleaseChecklist> allReleases = releaseService.getAllReleases(false);
		model.addAttribute("allReleases", allReleases);
		return "release/releaseList";
	}

	@RequestMapping(params = "op=addRelease")
	public String addOrUpdateRelease(HttpSession session, @ModelAttribute ReleaseChecklist release, @RequestParam(value = "items", required = false) List<Long> ids,
			@RequestParam(value = "releaseID", required = false) Long releaseID, Model model) {
		if (release.getName() == null) {
			List<Item> allItems = releaseService.getAllItems(false);
			// HashMap<String, List<Item>> categoriedItems = new HashMap<String, List<Item>>();
			ArrayList<Item> filterItems = new ArrayList<Item>();
			ArrayList<Item> speficItems = new ArrayList<Item>();
			for (Item item : allItems) {
				String category = item.getCategory();
				// List<Item> partItems = categoriedItems.get(category);
				// if (partItems == null) {
				// partItems = new ArrayList<Item>();
				// categoriedItems.put(category, partItems);
				// }
				// partItems.add(item);
				if (category != null) {
					if (!category.equalsIgnoreCase("Release Specific")) {
						filterItems.add(item);
					}
					else {
						speficItems.add(item);
					}
				}
			}
			model.addAttribute("filterItems", filterItems);
			model.addAttribute("speficItems", speficItems);
			return "release/releaseAdd";
		}

		if (ids != null && !ids.isEmpty()) {
			Set<ReleaseItem> releaseItems = new HashSet<ReleaseItem>();
			for (long id : ids) {
				ReleaseItem releaseItem = new ReleaseItem();
				Item item = new Item();
				item.setId(id);
				releaseItem.setItem(item);
				releaseItem.setRelease(release);
				releaseItems.add(releaseItem);
			}
			release.setReleaseItems(releaseItems);
		}

		if (releaseID != null) {
			release.setId(releaseID);
			releaseService.updateRelease(release);
		}
		else {
			releaseService.addRelease(release);
		}
		return backToReleaseHome;
	}

	@RequestMapping(params = "op=deleteRelease")
	public String deleteRelease(@RequestParam("releaseID") long releaseID) {
		ReleaseChecklist release = releaseService.getRelease(releaseID);
		if (release != null) {
			releaseService.logicDeleteRelease(release);
		}
		return backToReleaseHome;
	}

	@RequestMapping(params = "op=setCurrentRelease")
	public String setCurrentRelease(@RequestParam("releaseID") long releaseID) {
		releaseService.setCurrentRelease(releaseID);
		return backToReleaseHome;
	}

	@RequestMapping(params = "op=showRelease")
	public String showRelease(Model model, @RequestParam("releaseID") long releaseID) {
		List<Item> allItems = releaseService.getAllItems(false);
		// HashMap<String, List<Item>> categoriedItems = new HashMap<String, List<Item>>();
		ArrayList<Item> filterItems = new ArrayList<Item>();
		ArrayList<Item> speficItems = new ArrayList<Item>();
		for (Item item : allItems) {
			String category = item.getCategory();
			if (!category.equalsIgnoreCase("Release Specific")) {
				filterItems.add(item);
			}
			else {
				speficItems.add(item);
			}
		}
		ReleaseChecklist release = releaseService.getRelease(releaseID);
		Set<ReleaseItem> allExistingItems = release.getReleaseItems();
		// HashMap<String, List<Item>> categoriedItems = new HashMap<String, List<Item>>();
		ArrayList<Item> filterExistingItems = new ArrayList<Item>();
		ArrayList<Item> speficExistingItems = new ArrayList<Item>();
		ArrayList<Item> toRemovefilterItems = new ArrayList<Item>();
		ArrayList<Item> toRemoveSpeficItems = new ArrayList<Item>();
		for (ReleaseItem releaseItem : allExistingItems) {
			Item item = releaseItem.getItem();
			String category = item.getCategory();
			if (!category.equalsIgnoreCase("Release Specific")) {
				filterExistingItems.add(item);
				Item newItem = new Item();
				newItem.setId(item.getId());
				toRemovefilterItems.add(newItem);
			}
			else {
				speficExistingItems.add(item);
				Item newItem = new Item();
				newItem.setId(item.getId());
				toRemoveSpeficItems.add(newItem);
			}
		}

		filterItems.removeAll(toRemovefilterItems);
		speficItems.removeAll(toRemoveSpeficItems);

		model.addAttribute("filterItems", filterItems);
		model.addAttribute("speficItems", speficItems);
		model.addAttribute("filterExistingItems", filterExistingItems);
		model.addAttribute("speficExistingItems", speficExistingItems);
		model.addAttribute("release", release);
		return "release/releaseShow";
	}
	@RequestMapping(params = "op=currentRelease")
	public String currentRelease(Model model) {
		return showCurrentReleaseWithFilter(model, releaseService.getCurrentReleaseExcludeNA());
	}

	@RequestMapping(params = "op=todo")
	public String todo(Model model) {
		model.addAttribute("filter", "todo");
		return showCurrentReleaseWithFilter(model, releaseService.getToDo());
	}

	@RequestMapping(params = "op=na")
	public String na(Model model) {
		model.addAttribute("filter", "na");
		return showCurrentReleaseWithFilter(model, releaseService.getNA());
	}

	@RequestMapping(params = "op=next3Days")
	public String nextThreeDays(Model model) {
		model.addAttribute("filter", "next3Days");
		return showCurrentReleaseWithFilter(model, releaseService.getNextThreeDays());
	}
	
	@RequestMapping(params = "op=allCurrentReleaseItems")
	public String allCurrentReleaseItems(Model model) {
		model.addAttribute("filter", "all");
		return showCurrentReleaseWithFilter(model, releaseService.getCurrentReleaseItems());
	}

	@RequestMapping(params = "op=myReleaseItems")
	public String tmyReleaseItemsodo(Model model, @RequestParam("ownerID") long ownerID) {
		model.addAttribute("filterOwnerID", ownerID);
		return showCurrentReleaseWithFilter(model, releaseService.getReleaseItemsByOwner(ownerID));
	}
	
	private String showCurrentReleaseWithFilter(Model model, List<ReleaseItem> releaseItems) {
		ReleaseChecklist currentRelease = releaseService.getCurrentRelease();
		if (currentRelease == null) {
			model.addAttribute("errorMessage", "There are not current release found, you have to set one.");
			return listRelease(model);
		}
		model.addAttribute("allOwners", releaseService.getAllOwners(false));
		model.addAttribute("releaseItems", releaseItems);
		model.addAttribute("currentRelease", currentRelease);
		return "release/currentRelease";
	
	}

	@RequestMapping(params = "op=updateStatus")
	public String updateReleaseItemStatus(Model model, @RequestParam("releaseItemID") long releaseItemID, @RequestParam("status") String status) {
		ReleaseItem releaseItem = releaseService.getReleaseItem(releaseItemID);
		releaseItem.setStatus(status);
		releaseService.updateReleaseItem(releaseItem);
		return "success";
	}

	@RequestMapping(params = "op=updateReleaseItemOwner")
	public String updateReleaseItemOwner(Model model, @RequestParam("releaseItemID") long releaseItemID, @RequestParam("ownerID") long ownerID) {
		ReleaseItem releaseItem = releaseService.getReleaseItem(releaseItemID);
		Owner owner = new Owner();
		owner.setId(ownerID);
		releaseItem.setOwner(owner);
		releaseService.updateReleaseItem(releaseItem);
		return "success";
	}

	@RequestMapping(params = "op=updateDueDate")
	public String updateReleaseItemDueDate(Model model,@ModelAttribute ReleaseItem releaseItem, @RequestParam("releaseItemID") long releaseItemID) {
		ReleaseItem existingReleaseItem = releaseService.getReleaseItem(releaseItemID);
		existingReleaseItem.setDueDate(releaseItem.getDueDate());
		releaseService.updateReleaseItem(existingReleaseItem);
		return "success";
	}

	@RequestMapping(params = "op=updateComments")
	public String updateReleaseItemComments(Model model, @RequestParam("releaseItemID") long releaseItemID, @RequestParam("comments") String comments) {
		ReleaseItem existingReleaseItem = releaseService.getReleaseItem(releaseItemID);
		existingReleaseItem.setComments(comments);
		releaseService.updateReleaseItem(existingReleaseItem);
		return "success";
	}

	@RequestMapping(params = "op=takeSnapshot")
	public String takeSnapshot() {
		releaseService.takeSnapshot();
		return "success";
	}

	@RequestMapping(params = "op=getAllSnapshots")
	public String getAllSnapshots(Model model) {
		List<ReleaseChecklistSnapshot> allSnapshots = releaseService.getAllSnapshots();
		model.addAttribute("allSnapshots", allSnapshots);
		return "release/snapshotList";
	}

	@RequestMapping(params = "op=displaySnapshot")
	public String displaySnapshot(Model model, @RequestParam("snapshotID") long snapshotID) {
		ReleaseChecklistSnapshot snapshot = releaseService.getSnapshot(snapshotID);
		model.addAttribute("snapshot", snapshot);
		return "release/snapshotShow";
	}
	
	@RequestMapping(params = "op=reorderItemSequence")
	public String reorderItemSequence() {
		releaseService.reOrderItemSequence();
		return backToItemHome;
	}
}
