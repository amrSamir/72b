/**
 * 
 */
package com.OJToolkit.server.engine;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;


public class SPOJ implements Judge{

        @Override
        public ArrayList<Problem> getAllProblems() throws Exception {
                ArrayList<Problem> ret = new ArrayList<Problem>();
                String[] arr = {"classical" , "challenge" , "partial" , "tutorial"};
                for(int i = 0 ; i < arr.length ; i ++){

                        for(int st = 0 ; ; st +=50){
                                URL siteUrl = new URL("http://www.spoj.pl/problems/" + arr[i] + "/sort=0,start="+st);
                                HttpURLConnection conn = (HttpURLConnection) siteUrl.openConnection();
                                conn.setRequestMethod("POST");
                                conn.setDoOutput(true);
                                conn.setDoInput(true);
                                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                            String line = "";
                            int nop = 0;
                            while((line = in.readLine()) != null){
                                @SuppressWarnings("unused")
                                        int ind ;
                                if((ind = line.indexOf("problemrow")) != -1)
                                {
                                        int ii = 0;
                                        Problem p = new Problem();
                                        for(int k = 1 ; ii < 3 ; k ++)
                                        {
                                                line = in.readLine();
                                                String tem = "";
                                                if(k == 3){
                                                        int in2 = line.indexOf("\"");
                                                        for(int j = in2+1 ; line.charAt(j) != '\"' ; j ++){
                                                                tem += line.charAt(j);
                                                        }
                                                        p.setUrl("http://spoj.pl" + tem);
                                                        int in3 = line.indexOf("<b>");
                                                        tem = "";
                                                        for(int j = in3+3 ; j < line.length() &&line.charAt(j) != '<' ; j++){
                                                                tem += line.charAt(j);
                                                        }
                                                        p.setName(tem);
                                                        break;
                                                }
                                        }
                                        nop++;
                                        p.setType(arr[i]);
                                        int t = 0;
                                        String tem = "";
                                        for(int l = 0 ; l < p.getUrl().length() ; l ++)
                                        {
                                                if(t == 4 &&p.getUrl().charAt(l) != '/' ){
                                                        tem += p.getUrl().charAt(l);
                                                }
                                                else if(t > 4){
                                                        break;
                                                }
                                                if(p.getUrl().charAt(l) == '/')
                                                        t ++;
                                        }
                                        p.setId(tem);
                                        ret.add(p);
                                }
                            }
                                System.out.println(ret.size() + " " + i) ;
                                if(nop != 50)   
                                break;
                        }
                }
                return ret;
        }

        @Override
        public Submission getLastSubmission(String coderId , String pass) throws Exception {
                HashMap<String, String>ret = new HashMap<String, String>();
                URL siteUrl = new URL("http://www.spoj.pl/status/"+coderId+"/");
                HttpURLConnection conn = (HttpURLConnection) siteUrl.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                out.writeBytes("");
                out.flush();
                out.close();
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String tem , teto = "<td class=\"status_sm\">" ;
                String [] arr = {"DATE" , "PROBLEM" ,"RESULT" , "TIME" , "MEM" , "LANG"};
                int ind = 0 , ln = 1 ;
                boolean flag = false;
        while((tem = in.readLine()) != null && ind != arr.length)
        {
                @SuppressWarnings("unused")
                        int temp;
                if((temp = tem.indexOf(teto)) != -1){
                        flag = true;
                }
                if(flag)
                {
                        if(ln == 3){ // For The Date.
                                ret.put(arr[ind++], tem);
                        }
                        else if(ln == 10){ // For the Result
                                String s = "";
                                for(int i = 0 ; i < tem.length() ; i ++)
                                        if((tem.charAt(i)>= 'a' && tem.charAt(i) <= 'z') || tem.charAt(i) == ' ')
                                                s += tem.charAt(i);
                                ret.put(arr[ind++], s);
                                if(tem.equals("compilation error"))
                                        break;
                        }
                        else if(ln == 6){ // For the problem's link.
                                String s =      "";
                                int ch = 0;
                                for(int i = 13 ;; i ++  ){
                                        if(tem.charAt(i) == '/')
                                                ch++;
                                        else if(ch == 2)
                                                s += tem.charAt(i);
                                        else if(ch > 2)
                                                break;
                                }
                                ret.put(arr[ind++], s);
                        }
                        else if(ln == 19){ // For the running time.
                                String s = "";
                                for(int i = 0 ; i < tem.length();i ++){
                                        if(tem.charAt(i) == '.' || (tem.charAt(i) >= '0' && tem.charAt(i) <= '9'))
                                                s += tem.charAt(i);
                                }
                                ret.put(arr[ind++], s);
                        }
                        else if(ln == 22){ // For the memory used.
                                String s = "";  
                                for(int i = 0 ; i < tem.length() ; i ++)
                                        if((tem.charAt(i)>= '0' && tem.charAt(i) <= '9') || 
                                                        tem.charAt(i) == '.' || tem.charAt(i) == 'M')
                                                s += tem.charAt(i);
                                ret.put(arr[ind++], s);
                        }
                        else if(ln == 25){
                                int r = 0;
                                String s= "";
                                for(int i = 0 ; i < tem.length() ; i ++){
                                        if(tem.charAt(i) == '>' || tem.charAt(i) == '<')
                                                r ++;
                                        else if(r == 2)
                                                s += tem.charAt(i);
                                        else if(r > 2)
                                                break;
                                }
                                ret.put("LANG", s);
                 break;
                        }
                        ln ++;
                }
        }
        in.close();
        conn.disconnect();
                return new Submission(ret.get("PROBLEM"), ret.get("DATE"), ret.get("TIME"), ret.get("MEM"), ret.get("RESULT") , ret.get("LANG"));
        }

        @Override
        public Problem getProblemInfo(String problemId) throws Exception {
                HashMap<String, Integer> temp = new HashMap<String, Integer>();
                String arr[] = {"Users accepted" , "Submissions", "Accepted" , "Wrong Answer" , "Compile Error" , "Runtime Error" ,
                                "Time Limit Exceeded"};
                URL siteUrl = new URL("http://www.spoj.pl/ranks/" + problemId + "/");
                HttpURLConnection conn = (HttpURLConnection) siteUrl.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line = "";
                int ind = 0;
                boolean flag = false;
                String name = "" , id = "/problems/"+ problemId+"\">";
                while((line = in.readLine()) != null){
                        int tem;
                        if((tem = line.indexOf(id)) != -1){
                                for(int k = tem+id.length() ; line.charAt(k) != '<' ; k ++)
                                        name += line.charAt(k);
                        }
                        if(line.indexOf("lightrow") != -1)
                                flag = true;
                        else if(flag){
                                String n = "";
                                for(int i = 0 ; i < line.length() ; i ++){
                                        if(line.charAt(i) >= '0' && line.charAt(i) <= '9')
                                                n += line.charAt(i);
                                }
                                temp.put(arr[ind++], Integer.parseInt(n));
                        }
                        if(ind == arr.length)
                                break;
                }
                conn.disconnect();
                Integer x = (temp.get((Object)"Submissions")-temp.get((Object)"Accepted"));
                Problem ret = new Problem(problemId, "http://www.spoj.pl/problems/"+problemId, name, temp.get((Object)"Accepted").toString(), x.toString());
                return ret;     
        }

        @Override
        public ArrayList<String> getProblemsSolved(String coderId) throws Exception      {
                URL site = new URL("http://www.spoj.pl/users/" + coderId);
                ArrayList<String> ret = new ArrayList<String>();
                HttpURLConnection conn = (HttpURLConnection) site.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                conn.setDoInput(true);
        DataOutputStream out = new  DataOutputStream(conn.getOutputStream());;
        out.writeBytes("");
        out.flush();
        out.close();
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String tem = "";
        StringBuilder page = new StringBuilder();
        while((tem = in.readLine()) != null)
                page.append(tem+"\n");
        in.close();
        conn.disconnect();
        int s , ch = 0 , chs = 0;
        String f = "<td align=\"left\" width=\"14%\"><a href=";
        String temp = "" , prob = "";
        while((s = page.indexOf(f))!=-1){
                page.setCharAt(s, '&');
                s += f.length();
                temp = "";
                prob = "";
                ch = chs = 0;
                boolean flagP = false;
                for(int k = s ; ch != 2 ; k ++){
                        if(page.charAt(k) == '"')
                        {
                                ch ++;
                                continue;
                        }
                        else if(page.charAt(k) == '/')
                                chs ++;
                        else if(page.charAt(k) == ',')
                                flagP = false;

                        temp += page.charAt(k);
                        if(flagP)
                                prob += page.charAt(k);
                        if(chs == 2)
                        {
                                flagP = true ;
                                chs = 0;
                        }
                }
                if(prob.equals(""))
                        continue;
                ret.add("http://spoj.pl" + temp);
        }
        return ret;
        }

        @Override
        public void submitProblem(String coderId, String password,
                        String problemId, String languageId, String code) throws IOException {
                URL siteUrl = new URL("http://www.spoj.pl/submit/complete/");
                HttpURLConnection conn = (HttpURLConnection) siteUrl.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                String[] params = {"login_user" , "password" , "problemcode" , "file" , "lang" , "submit"};
                String[] values = {coderId , password , problemId , code, languageId , "Send"};
                StringBuilder content = new StringBuilder();
                for(int i = 0 ; i < params.length ; i ++)
                {
                        if(i != 0)
                                content.append("&");
                        content.append(params[i] + "=" + values[i]);
                }
                out.writeBytes(content.toString());
                out.flush();
                out.close();
                conn.getInputStream();
                conn.disconnect();
        }

}
