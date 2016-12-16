package com.onemore.vo;

public class InvitationsSummaryVO {

	private Integer pendingInvites;
	private Integer confirmed;
	private Integer pendingApproval;
	private Integer declined;
	private Integer seat;
	
	public Integer getPendingInvites() {
		return pendingInvites;
	}
	public void setPendingInvites(Integer pendingInvites) {
		this.pendingInvites = pendingInvites;
	}
	public Integer getPendingApproval() {
		return pendingApproval;
	}
	public void setPendingApproval(Integer pendingApproval) {
		this.pendingApproval = pendingApproval;
	}
	public Integer getConfirmed() {
		return confirmed;
	}
	public void setConfirmed(Integer confirmed) {
		this.confirmed = confirmed;
	}
	public Integer getDeclined() {
		return declined;
	}
	public void setDeclined(Integer declined) {
		this.declined = declined;
	}
	public Integer getSeat() {
		return seat;
	}
	public void setSeat(Integer seat) {
		this.seat = seat;
	}
	
}
