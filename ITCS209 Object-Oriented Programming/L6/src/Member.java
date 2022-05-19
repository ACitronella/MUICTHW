import java.util.ArrayList;

public class Member {
    private String email;
    private String password;
    private ArrayList<Video> vdoList;
    private final String PRINTING_FORMAT = "Email: %s (pwd: %s)\nList of Videos";
    private final String DELETE_VIDEO_FORMAT = "Title: %s URL: %sis successfully removed.";
    private final String PRINTING_VIDEO_FORMAT = "[%d] Title: %s URL: %s";


    public Member(String email, String password){
        this.email = email;
        this.password = password;
        this.vdoList = new ArrayList<>();
    }
    
    public boolean addVideo(Video vdo){
        if(vdo != null){
            this.vdoList.add(vdo);
            return true;
        }
        return false;
    }

    public boolean removeVideo(Video vdo){
        boolean isFound = false;
        for(int i = 0; i < this.vdoList.size(); i++){
            if(vdo.isEqual(this.vdoList.get(i))){
                Video v = this.vdoList.remove(i);
                System.out.println(String.format(DELETE_VIDEO_FORMAT, v.getTitle(), v.getURL()));
                isFound = true;
                break;
            }
        }
        return isFound;
    }

    public void printMemberInfo(){
        System.out.println(String.format(PRINTING_FORMAT, this.email, this.password));
        for(int i = 0; i < this.vdoList.size(); i++){
            Video vdo = this.vdoList.get(i);
            System.out.println(String.format(PRINTING_VIDEO_FORMAT, i+1, vdo.getTitle(), vdo.getURL()));
        }
    }
    

}
