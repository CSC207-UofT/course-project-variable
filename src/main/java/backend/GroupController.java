import java.util.HashMap;
import java.util.ArrayList;

public class GroupController {

    private GroupManager manager;

    public GroupController() {
        this.manager =  new GroupManager();


    }



    public Group createGroup(ArrayList<Person> groupMembers, String groupName){
         return (this.manager).CreateGroup(groupMembers, groupName);
    }


    public boolean addToGroup(Student member, String groupID){

        return (this.manager).addToGroup(member, groupID);

    }

    public boolean removeMember(Student member, String groupID){

        return (this.manager).removeGroupMember(member, groupID);

    }



    public String getID(Group group) {

        return this.manager.getID(group);


    }


    public Group getGroup(String id) {
        return this.manager.getGroup(id);

    }

    public ArrayList<ArrayList<Object>> getStudentGroups(Student currStudent) {

        return this.manager.getStudentGroups(currStudent);




    }


    public ArrayList<String> getStudentUsername(String gID) {

        return this.manager.getStudentUsername(gID);


    }
    public ArrayList<ArrayList<Object>> getFreeTimes(String gID, Float date, String day) {

        return this.manager.getFreeTimes(gID, date, day);


    }


}

