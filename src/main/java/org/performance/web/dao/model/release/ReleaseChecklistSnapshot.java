package org.performance.web.dao.model.release;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class ReleaseChecklistSnapshot implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 228819347073579377L;

	@Id
	@GeneratedValue
	private long id;
	
	private String name;
	
	@Column(length = Integer.MAX_VALUE)
	private String value;
	
	private Date createdDate;
	
	transient private List<ReleaseItemBean> releaseItems;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "release_id")
	private ReleaseChecklist release;
	
	private String type;

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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public ReleaseChecklist getRelease() {
		return release;
	}

	public void setRelease(ReleaseChecklist release) {
		this.release = release;
	}

	public List<ReleaseItemBean> getReleaseItems() {
		return releaseItems;
	}

	public void setReleaseItems(List<ReleaseItemBean> releaseItems) {
		this.releaseItems = releaseItems;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
