
public class FreeMember extends Member {
    private final int FREE_LIMITED_VDOs = 3;
    private int numDownloadedVDO;
    private String ERROR_FORMAT = "Title: %s URL: %s\ncannot be downloaded because the number of the video is reaching the limit.";

    public FreeMember(String email, String password){
        super(email, password);
        this.numDownloadedVDO = 0;
        
    }
    
    @Override
    public boolean addVideo(Video vdo){
        if(this.numDownloadedVDO < FREE_LIMITED_VDOs){
            this.numDownloadedVDO++;
            return super.addVideo(vdo);
        }
        else if(vdo == null){
            return false;
        }
        
        System.out.println(String.format(ERROR_FORMAT, vdo.getTitle(), vdo.getURL()));        
        return false;
    }

    @Override
    public boolean removeVideo(Video vdo){
        if(super.removeVideo(vdo)){
            this.numDownloadedVDO--;
            return true;
        }
        return false;
    }

    @Override
    public void printMemberInfo(){
        System.out.println("---- FREE MEMBER ----");
        super.printMemberInfo();
        System.out.println("---------------------");
    }

    public int getNumVideo(){
        return this.numDownloadedVDO;
    }

}
