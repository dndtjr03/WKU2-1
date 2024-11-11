package testClass;
public class App {        
    public static void main(String[] args) throws Exception {
               
        String splus, sminus, smulti, sdiv;
               MultiDiv ob1 = new MultiDiv();
               splus = ob1.plus(50, 30);
               sminus = ob1.minus(50, 30);
               smulti = ob1.multi(50, 30);
               sdiv = ob1.div(50, 30);
               System.out.println(splus);
               System.out.println(sminus);
               System.out.println(smulti);
               System.out.println(sdiv);
            }
        }
