package model.entities;

import model.enums.MemberStatus;

public class Member {

    private String name;
    private String email;
    private String membershipId;
    private MemberStatus memberStatus;

    public Member(){}

    public Member(String name, String email, String membershipId) {
        this.name = name;
        this.email = email;
        this.membershipId = membershipId;
        this.memberStatus = MemberStatus.ACTIVE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(String membershipId) {
        this.membershipId = membershipId;
    }

    public MemberStatus getMemberStatus() {
        return memberStatus;
    }

    public void setMemberStatus(MemberStatus memberStatus) {
        this.memberStatus = memberStatus;
    }

    public boolean isSuspended(){
        return memberStatus == MemberStatus.ACTIVE;
    }
}
