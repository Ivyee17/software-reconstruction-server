package core;


import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *  ( or , or ) or num
 * nextpos ( = ( or num
 * nextpos , = ( or num
 * nextpos ) = , or )
 * nextpos num = num or , or )
 *
 * input_text=r'{ 1 ,{10,{30,40}},{3,4,5}}'
 */

public class BraceToTree {
//    public static RefactorNode refactorNode;
    public String str;

    /**
     * It can be optimized to return the integer if hashing function is designed pretty well.
     * Will be supported later
     * @param s
     * @return
     */
    public String headNameGeneration(String s){
        return s+'d';
    }
    public int parenthesesMatch(int start){
        int unmatchedLeftBrace=0;
        for(int i=start;i<str.length();i++){
            if(str.charAt(i)=='('){
                unmatchedLeftBrace++;
            }
            else if(str.charAt(i)==')'){
                unmatchedLeftBrace--;
                if(unmatchedLeftBrace==0){
                    return i;
                }
            }
        }
        return -1;
    }

    //"(( ((1,2,9),0,3,7),4,10) ,5,6,8,11,12)"
    public RefactorNode changeBrace(int pos){

        int startPosition=pos;
        int i=pos;
        RefactorNode head=new RefactorNode(null);
        while(i<str.length()){
            Logger.getGlobal().info( "i="+i);
            if(str.charAt(i)==','){
                Logger.getGlobal().info("startPosition = " + startPosition);
                if(str.charAt(startPosition)!='('){
                    Logger.getGlobal().info(str.substring(startPosition,i));
                    int parseResult=Integer.parseInt(str.substring(startPosition,i));
                    head.addChild(new RefactorNode( String.valueOf(parseResult)));

                }
                startPosition=i+1;

            }
            else if(str.charAt(i)=='('){
                Logger.getGlobal().info("recursive"+i);
                head.addChild(changeBrace(i+1));
                i=parenthesesMatch(i);
                Logger.getGlobal().info("nowi="+i);
            }
            else if(str.charAt(i)==')'){
                Logger.getGlobal().info(""+str.charAt(startPosition));
                if(str.charAt(startPosition)!='('){
                    int parseResult=Integer.parseInt(str.substring(startPosition,i));
                    head.addChild(new RefactorNode( String.valueOf(parseResult)));
                    Logger.getGlobal().info("out"+i);
                }
                head.setData(headNameGeneration(head.getNodes().get(0).getData()));
//                refactorNode=head;
                return head;
            }
            i+=1;

        }
        if(startPosition!=str.length() && str.charAt(startPosition)!='(') {
            int parseResult = Integer.parseInt(str.substring(startPosition));
            head.addChild(new RefactorNode(String.valueOf(parseResult)));
        }
        head.setData(head.getNodes().get(0).getData()+'c');
//        refactorNode=head;
        return head;
    }

    /**
     * If braceString structure is surrounded by parentheses, function will delete the outer brackets to simplify the result.
     * @param braceString Should be with/without braces. e.g. (2), (2,(3)), ...
     * @return the refactored structure
     */
    public RefactorNode convert(String braceString)  {
        str=braceString;
        if(braceString.charAt(0)=='('&&parenthesesMatch(0)==str.length()-1){
//            throw new Exception("Format error: Should be surrounded by braces");
            str=str.substring(1,str.length()-1);
            return changeBrace(4);
        }
        else{

            return changeBrace(4);
        }


    }
}
