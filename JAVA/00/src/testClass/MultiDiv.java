package testClass;

class MultiDiv extends Pl_Minus{
    int gop;
    double nanum;
    public String multi(int x, int y){
        gop = x * y;
        return "두 변수의 곱은 " + gop;
    }
    public String div(int x, int y){
        nanum = (double) x / y;
        return "두 변수를 나눈 값은 " + nanum;
    }
}

    @Override
    public String plus(int x, int y){
        return String.valueOf(x+y) + "재정의";
    }
