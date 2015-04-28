package dk.nicolajpedersen.raidaid.Data;

/**
 * Created by Nicolaj on 07-04-2015.
 */
public class WallShout {
    String message;
    Member member;

    public WallShout(String message,Member member){
        this.message = message;
        this.member = member;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
