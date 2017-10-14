package com.report1.kdh.calculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Stack;

public class MainActivity extends AppCompatActivity implements Button.OnClickListener{

    TextView value;  //계산 기록 값
    String valueRecord="";
    String postFix="";

    //계산 기록 저장할 배열 최대 10
    public String[] saveRecord =new String[10];
    int saveRecord_count=0;
    boolean operateTrue=true; // ()  (인지  )인지 판단  true->'('   false->')'
    boolean primeCheck=true;
    boolean checkPlus=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator_layout);

        value=(TextView)findViewById(R.id.value);

        final Integer[] btn={R.id.record, R.id.erase,R.id.number0, R.id.number1,R.id.number2,R.id.number3,R.id.number4,R.id.number5,R.id.number6,R.id.number7,R.id.number8,R.id.number9,
                R.id.initialize,R.id.operate,R.id.mod,R.id.div,R.id.mul,R.id.min,R.id.add,R.id.changePlusMinus,R.id.prime,R.id.result};
        Button[] btns=new Button[22];
        for(int i=0; i<btns.length; i++)
        {
            btns[i]=(Button)findViewById(btn[i]);
            btns[i].setOnClickListener(this);
        }

    }
    public void printValueRecord(String str)
    {
        value.setText(str);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.record:{
                Intent intent=new Intent(this, SaveRecord.class);
                // 배열 정보 보내주기
                intent.putExtra("record",saveRecord);
                startActivity(intent);
                break;
            }
            case R.id.erase: {
                //( 삭제해줄때
                if(valueRecord.charAt(valueRecord.length()-1)=='(')
                {
                    operateTrue=true;
                }
                else if(valueRecord.charAt(valueRecord.length()-1)==')')
                {
                    operateTrue=false;
                }
                else if(valueRecord.charAt(valueRecord.length()-1)=='_')
                {
                    checkPlus=true;
                }
                if(valueRecord !=null && valueRecord.length()>0 )
                {
                    valueRecord=valueRecord.substring(0,valueRecord.length()-1);
                    printValueRecord(valueRecord);
                }
                break;
            }
            case R.id.number0: {
                //길이 1이고 0만 있을경우 0을 눌러도 다시 0만 나오게
                if(valueRecord.length()==1 && valueRecord.charAt(0)=='0')
                {
                    valueRecord="";
                    valueRecord += "0";
                }
                else {
                    valueRecord += "0";
                }
                printValueRecord(valueRecord);
                break;
            }
            case R.id.number1: {
                //길이가 1이고 0이면은 0을 삭제하고 숫자를 넣는다.
                if(valueRecord.length()==1 && valueRecord.charAt(0)=='0') {
                    valueRecord="";
                    valueRecord += "1";
                }
                else
                {
                    valueRecord+="1";
                }
                printValueRecord(valueRecord);
                break;
            }
            case R.id.number2: {
                if(valueRecord.length()==1 && valueRecord.charAt(0)=='0') {
                    valueRecord="";
                    valueRecord += "2";
                }
                else
                {
                    valueRecord+="2";
                }
                printValueRecord(valueRecord);
                break;
            }
            case R.id.number3: {
                if(valueRecord.length()==1 && valueRecord.charAt(0)=='0') {
                    valueRecord="";
                    valueRecord += "3";
                }
                else
                {
                    valueRecord+="3";
                }
                printValueRecord(valueRecord);
                break;
            }
            case R.id.number4: {
                if(valueRecord.length()==1 && valueRecord.charAt(0)=='0') {
                    valueRecord="";
                    valueRecord += "4";
                }
                else
                {
                    valueRecord+="4";
                }
                printValueRecord(valueRecord);
                break;
            }
            case R.id.number5: {
                if(valueRecord.length()==1 && valueRecord.charAt(0)=='0') {
                    valueRecord="";
                    valueRecord += "5";
                }
                else
                {
                    valueRecord+="5";
                }
                printValueRecord(valueRecord);
                break;
            }
            case R.id.number6: {
                if(valueRecord.length()==1 && valueRecord.charAt(0)=='0') {
                    valueRecord="";
                    valueRecord += "6";
                }
                else
                {
                    valueRecord+="6";
                }
                printValueRecord(valueRecord);
                break;
            }
            case R.id.number7: {
                if(valueRecord.length()==1 && valueRecord.charAt(0)=='0') {
                    valueRecord="";
                    valueRecord += "7";
                }
                else
                {
                    valueRecord+="7";
                }
                printValueRecord(valueRecord);
                break;
            }
            case R.id.number8: {
                if(valueRecord.length()==1 && valueRecord.charAt(0)=='0') {
                    valueRecord="";
                    valueRecord += "8";
                }
                else
                {
                    valueRecord+="8";
                }
                printValueRecord(valueRecord);
                break;
            }
            case R.id.number9: {
                if(valueRecord.length()==1 && valueRecord.charAt(0)=='0') {
                    valueRecord="";
                    valueRecord += "9";
                }
                else
                {
                    valueRecord+="9";
                }
                printValueRecord(valueRecord);
                break;
            }
            case R.id.initialize: {
                valueRecord="";
                printValueRecord(valueRecord);
                operateTrue=true;
                primeCheck=true;
                checkPlus=true;
                break;
            }
            //()
            case R.id.operate: {
                if(operateTrue)
                {
                    if(valueRecord.length() !=0) {
                        char ch = valueRecord.charAt(valueRecord.length() - 1);
                        //마지막 문자가 연산자일경우는 괄호만  숫자일경우 *까지해서
                        //숫자일경우
                        if ('0' <= ch && ch <= '9') {
                            valueRecord += "*";
                            valueRecord += "(";
                            operateTrue = false;
                            printValueRecord(valueRecord);
                            primeCheck=true;
                            checkPlus=true;
                        }
                        //연산자일경우
                        else {
                            valueRecord += '(';
                            printValueRecord(valueRecord);
                            operateTrue = false;
                            primeCheck=true;
                            checkPlus=true;
                        }
                    }
                    else
                    {
                        valueRecord += '(';
                        printValueRecord(valueRecord);
                        operateTrue = false;
                    }
                }
                else
                {
                    valueRecord += ')';
                    printValueRecord(valueRecord);
                    operateTrue=true;
                }
                break;
            }
            case R.id.mod: {
                valueRecord+="%";
                printValueRecord(valueRecord);
                primeCheck=true;
                checkPlus=true;
                break;
            }
            case R.id.div: {
                //연산자도 저장해준다
                valueRecord+="/";
                printValueRecord(valueRecord);
                primeCheck=true;
                checkPlus=true;
                break;
            }
            case R.id.mul: {
                //연산자도 저장해준다
                valueRecord+="*";
                printValueRecord(valueRecord);
                primeCheck=true;
                checkPlus=true;
                break;
            }
            case R.id.min: {
                //연산자도 저장해준다
                valueRecord+="-";
                printValueRecord(valueRecord);
                primeCheck=true;
                checkPlus=true;
                break;
            }
            case R.id.add: {
                //연산자도 저장해준다
                valueRecord+="+";
                printValueRecord(valueRecord);
                primeCheck=true;
                checkPlus=true;
                break;
            }
            case R.id.changePlusMinus: {
                //양수일때 마이너스 붙이고 마이너스일때 양수 바꾸기
                if(checkPlus) {
                    if(valueRecord.length()>0) {
                        String post = "";
                        boolean isFirst = true;
                        for (int i = 0; i < valueRecord.length(); i++) {
                            //연산자 만나게되면 바꿔야되는 수를 다시 새로 넣어야되기떄문에 초기화
                            if (valueRecord.charAt(i) == '+' || valueRecord.charAt(i) == '-' || valueRecord.charAt(i) == '*' || valueRecord.charAt(i) == '/') {
                                post+=valueRecord.charAt(i);
                                post += '_';
                                isFirst = false;
                            } else {
                                post += valueRecord.charAt(i);
                            }
                        }
                        Log.d("?",post);
                        if (isFirst) {
                            valueRecord = "_" + post;
                        } else {
                            valueRecord = post;
                        }
                        checkPlus = false; //음수로 바꿔주기
                        printValueRecord(valueRecord);
                    }
                }
                else
                {
                    String post="";
                    if(valueRecord.length() >0) {
                        for (int i = 0; i < valueRecord.length(); i++) {
                            if (valueRecord.charAt(i) != '_') {
                                post += valueRecord.charAt(i);
                            }
                        }
                        valueRecord = post;
                        checkPlus = true; //양수로 바꿔주기
                        printValueRecord(valueRecord);
                    }
                }
                break;
            }
            case R.id.prime: {
                //아무것도없을때 . 입력하면 안나오게
                if(valueRecord.length() !=0) {
                    if(primeCheck==true) {
                        valueRecord += ".";
                        printValueRecord(valueRecord);
                        primeCheck = false;
                    }
                }
                break;
            }
            case R.id.result: {
                String temp;
                temp=valueRecord;
                temp+="=";
                changePostfix(valueRecord);  //postFix변화시키기
                Log.d("post", valueRecord);
                String str="";

                int num1=(int)calculate(postFix);
                double num=calculate(postFix);
                //정수로 표현
                if(num1==num)
                {
                    str+=String.valueOf(num1);
                    value.setText(str);
                }
                else {
                    str += String.valueOf(num);
                    value.setText(str);
                }
                valueRecord=str;
                postFix="";
                primeCheck=true;
                checkPlus=true;

                temp+=String.valueOf(num);
                if(saveRecord_count<10) {
                    saveRecord[saveRecord_count++] = temp;
                }
                //saveRecord_count가 10일 경우
                else
                {
                    for(int i=0; i<9; i++)
                    {
                        saveRecord[i]=saveRecord[i+1];
                    }
                    saveRecord[9]=temp;
                }

                break;
            }
        }
    }
    public void changePostfix(String str)
    {
        Stack<Character> s=new Stack<Character>();
        for(int i=0; i<str.length(); i++)
        {
            Log.d("asd", str);
            if(('0'<=str.charAt(i) && str.charAt(i)<='9') || str.charAt(i)=='.' || str.charAt(i)=='_')
            {
                int j;
                //숫자인데까지 더해주기
                for(j=i; j<str.length()&&(('0'<=str.charAt(j)&& str.charAt(j)<='9')||str.charAt(j)=='.' || str.charAt(j)=='_'); j++)
                {
                    postFix+=str.charAt(j);
                    Log.d("postFix",postFix);
                }
                postFix+="$";
                Log.d("postFix",postFix);
                i=j-1;
            }
            //연산자 일떄
            else
            {
                if(str.charAt(i)=='(')
                {
                    s.push( str.charAt(i));
                }
                else if(str.charAt(i)==')')
                {
                    while(s.peek() !='(')
                    {
                        postFix+=s.pop();
                        Log.d("postFix",postFix);
                        //s.pop();
                    }
                    s.pop();
                }
                //*,/ 인경우
                else if(str.charAt(i)=='*' || str.charAt(i)=='/')
                {
                    while(!s.empty() && (s.peek()=='*' ||s.peek()=='/' ||s.peek()=='%'))
                    {
                        postFix+=s.pop();
                        Log.d("postFix",postFix);
                        //s.pop();
                    }
                    s.push((str.charAt(i)));
                }
                //그 외 연산자인경우
                else
                {
                    while(!s.empty() && s.peek() !='(')
                    {
                        postFix+=s.pop();
                        Log.d("postFix",postFix);
                        //s.pop();
                    }
                    s.push(str.charAt(i));
                }
            }
        }

        //나머지 스택에있는 연산자 빼내기
        while(!s.empty())
        {
            postFix+=s.pop();
            Log.d("postFix",postFix);
            //s.pop();
        }
        postFix+="\n";
    }
    public double calculate(String str)
    {
        double num2=0;
        double num1=0;
        Stack<Double> s=new Stack<Double>();
        boolean check=true;
        for(Integer i=0; i<str.length(); i++)
        {
            if(str.charAt(i)=='$')
            {
                check=true;
                continue;
            }
            if(('0'<=str.charAt(i)&& str.charAt(i)<='9') || str.charAt(i)=='_')
            {
                if(check) {
                    double a = changeDouble(str, i);
                    Log.d("value", ""+a);
                    s.push(a);
                    check = false;
                }
            }
            else if(str.charAt(i)=='.' || str.charAt(i)=='$')
            {
                continue;
            }
            //연산자 만났을경우
            else
            {
                if(!s.empty()) {
                    num2 = s.pop();
                }
                //s.pop();
                if(!s.empty()) {
                    num1 = s.pop();
                }
                //s.pop();
                switch(str.charAt(i))
                {
                    case '+':
                    {
                        num1 += num2;
                        break;
                    }
                    case '-':
                    {
                        num1-=num2;
                        break;
                    }
                    case '*':
                    {
                        num1*=num2;
                        break;
                    }
                    case '/':
                    {
                        num1/=num2;
                        break;
                    }
                    case '%':
                    {
                        num1%=num2;
                        break;
                    }
                }
                //계산한 결과를 다시 넣어준다
                s.push(num1);
            }
        }

        return s.pop().doubleValue();
    }
    public  double changeDouble(String str, Integer index)
    {
        double temp;
        String num="";
        for(; str.charAt(index) != '$'; index++)
        {
            if( ('0' <= str.charAt(index) && str.charAt(index) <= '9') || str.charAt(index)=='.' || str.charAt(index)=='_')
            {
                if(str.charAt(index)=='_')
                {
                    num+="-";
                }
                else {
                    num += str.charAt(index);
                }
            }
        }
        temp=Double.parseDouble(num);
        return temp;
    }
}
