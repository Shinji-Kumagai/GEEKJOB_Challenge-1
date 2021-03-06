<%-- 
    Document   : JSP.Servlet2-8
    Created on : 2017/02/07, 17:18:32
    Author     : 将也
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP.Servlet2-8</title>
    </head>
    <body>
<%!
    //getPlofメソッドに3人分のプロフィールを定義する。idを引数にする。
    String[]getPlof(int id){
        
        //2人目のプロフィールの住所をnullの状態にする。
        String[] info = {null};
        String[] info1 = {"1111","A子","1111年11月11日","東京都東京区"};
        String[] info2 = {"2222","B男","2222年22月22日",null};
        String[] info3 = {"3333","C子","3333年33月33日","埼玉県埼玉区"};
        
        //引数idの値によって誰のプロフィールを選択するかの処理。
        switch(id){
            case 1111:
                info = info1;
                break;
            case 2222:
                info = info2;
                break;
            case 3333:
                info = info3;
                break;
        }
        return info;
}
%>
<%
    //idsの配列に1111、2222、3333のidを格納する。
    int[] ids = {1111,2222,3333}; 
     
    //拡張for文を使う事で、idsに格納した3つのid分処理させる。
    for(int id : ids){
        
        //getPlofメソッドの呼び出し、引数idは拡張for文で処理される1111、2222、3333が順に渡される。
        String[]prof = getPlof(id);
        
        String name = prof[1];
        String birth = prof[2];
        String add = prof[3];
        
        //addにnullを見つけた場合、continueで次のループ処理に飛ぶ。
        if(add == null){
            continue;
        }
        //今回はB男の住所が「null」の為、A子とC子のプロフィールが順に表示される。
        out.print("名前："+name+"<br>"+"生年月日："+birth+"<br>"+"住所："+add+"<br><br>");
    }
%>
    </body>
</html>
