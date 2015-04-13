package dk.nicolajpedersen.raidaid.Data;

/**
 * Created by Nicolaj on 13-04-2015.
 */
public class User {
    String userName;

    public int getMemberRank() {
        return memberRank;
    }

    public void setMemberRank(int memberRank) {
        this.memberRank = memberRank;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    int memberRank;

    public String getUserName() {
        return userName;
    }
}
