package raspruner.CLI;


/**
 * Created by BD-Loen on 2017/4/13.
 */
public class cltForRasp {
    private  void seeMemory(){
        Double nm = CLIT.getMemery();
        System.out.println(nm);
    }

    public static void main(String[] args) {
        System.out.println("hellow word");
        cltForRasp cfr = new cltForRasp();
        cfr.seeMemory();
    }
}
