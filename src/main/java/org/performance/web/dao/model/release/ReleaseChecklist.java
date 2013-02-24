package org.performance.web.dao.model.release;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class ReleaseChecklist implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 228819347073579377L;

	@Id
	@GeneratedValue
	private long id;
	private String name;
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "release", orphanRemoval=true)
	@OrderBy("id")
	private Set<ReleaseItem> releaseItems;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date qaStableDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date stageDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date prodDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdDate;

	private boolean isCurrentRelease;

	private boolean isDeleted;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<ReleaseItem> getReleaseItems() {
		return releaseItems;
	}

	public void setReleaseItems(Set<ReleaseItem> releaseItems) {
		this.releaseItems = releaseItems;
	}

	public Date getQaStableDate() {
		return qaStableDate;
	}

	public void setQaStableDate(Date qaStableDate) {
		this.qaStableDate = qaStableDate;
	}

	public Date getStageDate() {
		return stageDate;
	}

	public void setStageDate(Date stageDate) {
		this.stageDate = stageDate;
	}

	public Date getProdDate() {
		return prodDate;
	}

	public void setProdDate(Date prodDate) {
		this.prodDate = prodDate;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public boolean isCurrentRelease() {
		return isCurrentRelease;
	}

	public void setCurrentRelease(boolean isCurrentRelease) {
		this.isCurrentRelease = isCurrentRelease;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
