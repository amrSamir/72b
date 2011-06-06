/**
 * 
 */
package com.OJToolkit.server.engine;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;


/**
 * @author 72B
 * Jun 6, 2011
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
    public void submitProblem(String coderId, String password,
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
    }

    /* (non-Javadoc)
     * @see Engine.Judge#getLastSubmission(java.lang.String, java.lang.String)
     */
    @Override
    public Submission getLastSubmission(String coderId, String password)
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
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            out.writeBytes("");
            out.flush();
            out.close();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String tem = in.readLine();
            Problem p;
            for(Integer i = 1000 ; tem.indexOf(i.toString()) != -1; i ++){
                    p = new Problem(i.toString(), "http://acm.timus.ru/problem.aspx?space=1&num="+i, null, null, null);
                    int ind = -1;
                    for(int k = 0 ; k < 2 ; k ++)
                            ind = tem.indexOf(i.toString() , ++ ind);
                    String res = "";
                    for(int j = ind+i.toString().length()+2 ; tem.charAt(j) != '<' && tem.charAt(j) != '>' ; j ++)
                            res += tem.charAt(j);
                    p.setName(res);
                    ret.add(p);
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

}

