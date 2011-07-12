/**
 * 
 */
package com.OJToolkit.server.engine;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

/**
 * @author root
 *
 */
public class Timus implements Judge {

        /* (non-Javadoc)
         * @see Engine.Judge#submitProblem(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
                        3 --> Pascal    
                        7 --> Java
                        9 --> C
                        10 --> C++
                        11 --> C#
         */
        @Override
        public Long submitProblem(String coderId, String password,
                        String problemId, String languageId, String code)
                        throws IOException {
                // submit.aspx?space=1
                URL siteUrl = new URL("http://acm.timus.ru/submit.aspx?space=1");
                HttpURLConnection conn = (HttpURLConnection) siteUrl.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                String[] params = {"JudgeID" , "ProblemNum" , "Source" , "Language" , "Action" , "SpaceID"};
                String[] values = {coderId , problemId , code, languageId , "submit" , "1"};
                StringBuilder content = new StringBuilder();
                for(int i = 0 ; i < params.length ; i ++)
                {
                        if(i != 0)
                                content.append("&");
                        content.append(params[i] + "=" + URLEncoder.encode(values[i] , "UTF-8"));
                }
                out.writeBytes(content.toString());
                out.flush();
                out.close();
                conn.getInputStream();
                conn.disconnect();
                return null;
        }

        /* (non-Javadoc)
         * @see Engine.Judge#getLastSubmission(java.lang.String, java.lang.String)
         */
        @Override
        public Submission getLastSubmission(String coderId, String password, String ids)
                        throws Exception {
                Submission ret = new Submission();
                URL siteUrl = new URL("http://acm.timus.ru/status.aspx?author="+coderId.substring(0 , coderId.length()-2));
                HttpURLConnection conn = (HttpURLConnection) siteUrl.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                out.writeBytes("");
                out.flush();
                out.close();
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String tem = in.readLine();
                String arr[]= {"verdict" , "language" , "runtime" , "memory" , "problem" ,"date"};
                int [] num1 = {1 , 2 , 2 , 2 , 3 , 3};
                int [] num2 = {1 , 1 , 1 , 1 , 3 , 9};
                for(int i = 0 ; i < arr.length ; i ++)
                {
                        String temp = "";
                        int ind = -1;
                        for(int k = 0 ; k < num1[i] ; k ++)
                                ind = tem.indexOf(arr[i], ++ ind) ;
                        for(int c = 0 ; c != num2[i] ; c += (tem.charAt(ind) == '>'||tem.charAt(ind)== '<') ? 1 : 0 , ind ++);
                        for( ; tem.charAt(ind) != '>' && tem.charAt(ind) != '<' ; ind ++)
                                temp += tem.charAt(ind);
                        if(i == 0)
                                ret.setStatus(temp.equals("") ? "compilation error":temp);
                        else if(i == 1)
                                ret.setLanguage(temp);
                        else if(i == 2)
                                ret.setRuntime(temp);
                        else if(i == 3)
                                ret.setMemoryUsed(temp);
                        else if(i == 4){
                                ret.setProblemId(temp.substring(0, temp.indexOf(".")));
                        }
                        else 
                                ret.setDate(temp);
                }
                return ret;
        }

        /* (non-Javadoc)
         * @see Engine.Judge#getAllProblems()
         */
        @Override
        public ArrayList<Problem> getAllProblems() throws Exception {
                ArrayList<Problem> ret = new ArrayList<Problem>();

                URL siteUrl = new URL("http://acm.timus.ru/problemset.aspx?space=1&page=all");
                HttpURLConnection conn = (HttpURLConnection) siteUrl.openConnection();
                conn.setRequestMethod("GET");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                out.writeBytes("");
                out.flush();
                out.close();
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String page = in.readLine();
                for(Integer i = 1000 ; i < 3000 ; i ++){
                        Matcher m = Pattern.compile("problem.aspx\\?space=1&amp;num="+i.toString()+"\">([^<]+)").matcher(page);
                        if(!m.find())
                                continue;
                        ret.add(new Problem(i.toString(), "http://acm.timus.ru/problem.aspx?space=1&num="+i.toString(), m.group(1), "", ""));
                }
                return ret;
        }

        /* (non-Javadoc)
         * @see Engine.Judge#getProblemsSolved(java.lang.String)
         */
        @Override
        public ArrayList<String> getProblemsSolved(String coderId)
                        throws MalformedURLException, Exception {
                // TODO Auto-generated method stub
                return null;
        }

        /* (non-Javadoc)
         * @see Engine.Judge#getProblemInfo(java.lang.String)
         */
        @Override
        public Problem getProblemInfo(String problemId) throws Exception {
                // TODO Auto-generated method stub
                return null;
        }

    @Override
    public boolean signIn(String username, String password) throws Exception { 
                URL siteUrl = new URL("http://acm.timus.ru/authedit.aspx");
                HttpURLConnection conn = (HttpURLConnection) siteUrl.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                out.writeBytes("Action=edit&JudgeID="+username+"&Password=");
                out.flush();
                out.close();
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String tem;
                String regex = "<FONT COLOR=\"Red\">Invalid JUDGE_ID</FONT>";
                while((tem = in.readLine()) != null){
                        if(tem.indexOf(regex) != -1)
                                return false;
                }
        return true;
    }

    @Override
    public boolean signOut(String username) {
        return true;
    }

    @Override
    public ArrayList<ProblemText> getProblemTexts() throws Exception {
//        throw new UnsupportedOperationException("Not supported yet.");
                ArrayList<ProblemText> ret = new ArrayList<ProblemText>();
                String ps = "<H3 CLASS=\"problem_subtitle\">";
                String dis = "<DIV ID=\"problem_text\">([\\s\\S]+)"+ps+"Input</H3>";
                String input = ps + "Input</H3>([\\s\\S]+)"+ps+"Output</H3>";
                String output = ps + "Output</H3>([\\s\\S]+)("+ps+"Samples?</H3>)?(<DIV CLASS=\"problem_source\">)?";
                String sampleT = ps + "Samples?</H3>([\\s\\S]+)"+ "<DIV CLASS=\"problem_source\">";
                
                GetMethod g = new GetMethod();
                HttpClient h = new HttpClient();
                Scanner s = new Scanner(new File(""));
                String line;
                s.nextLine();
                PrintWriter p = new PrintWriter(new File(
                                "/home/workspace/JudgesEngine/src/ProblemsTextFiles/Timus.txt"));
                int f = 0;
                while (s.hasNext()) {
                        f++;
                        if (f % 10 == 0)
                                p.flush();
                        line = s.nextLine();
                        g = new GetMethod("http://acm.timus.ru/problem.aspx?space=1&num="
                                        +  line.substring(0, line.indexOf("|") - 1));
                        h.executeMethod(g);
                        String gg = g.getResponseBodyAsString();
                        String d = match(gg , dis, 1);
                        String i = match(gg , input, 1);
                        String o = match(gg , output, 1);
                        String ss= match(gg , sampleT, 1);
//                      System.out.println(i + " \n" + ss);
                        if (i.equals("la2a") || o.equals("la2a") ) {
                                p.write(line.substring(0, line.indexOf("|") - 1) + "||||||false||||||\n");
                                System.err.println(line.substring(0, line.indexOf("|") - 1) + "||||||false||||||\n");
                                ret.add(new ProblemText("", "", "", "", false, d));
                                p.write(d + "\n");
                        } else {
                                p.write(line.substring(0, line.indexOf("|") - 1) + "||||||true||||||\n");;
                                System.out.println(line.substring(0, line.indexOf("|") - 1) + "||||||true||||||");
                                p.write( d
                                                + "\n\n*******InputConstraints*******\n\n" + i
                                                + "\n\n*******OutputConstraints*******\n\n" + o
                                                + "\n\n*******IOTestCases*******\n\n" + ss + "\n");
//                              System.out.println( d
//                                              + "\n\n*******InputConstraints*******\n\n" + i
//                                              + "\n\n*******OutputConstraints*******\n\n" + o
//                                              + "\n\n*******IOTestCases*******\n\n" + ss);
                                ret.add(new ProblemText(d, i, o, ss, true, ""));
                                
                        }
                         System.out.println("______________________________________________________");
                        p.write("______________________________________________________\n");
                }
                p.flush();
                p.close();
        return null;
    }
        private String match(String text, String regex, int g) {
                Matcher m = Pattern.compile(regex).matcher(text);
                if (m.find())
                        return m.group(g);
                return "la2a";
        }
}
