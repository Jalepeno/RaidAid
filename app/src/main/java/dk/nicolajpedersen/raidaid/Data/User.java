package dk.nicolajpedersen.raidaid.Data;

/**
 * Created by Nicolaj on 13-04-2015.
 */
public class User {
    String userName;
    int memberRank;

    public User(String userName, int memberRank) {
        this.userName = userName;
        this.memberRank = memberRank;
    }

    public int getMemberRank() {
        return memberRank;
    }

    public void setMemberRank(int memberRank) {
        this.memberRank = memberRank;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}
