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
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class LiveArchive implements Judge {

        private ArrayList<String> getResponse(String url, String request)
                        throws Exception {
                URL siteUrl = new URL(url);
                HttpURLConnection conn = (HttpURLConnection) siteUrl.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                out.writeBytes(request);
                out.flush();
                out.close();
                BufferedReader in = new BufferedReader(new InputStreamReader(
                                conn.getInputStream()));
                String tem = "";
                StringBuilder page = new StringBuilder();
                while ((tem = in.readLine()) != null)
                        page.append(tem + "\n");
                in.close();
                conn.disconnect();
                ArrayList<String> ret = new ArrayList<String>();
                Matcher m = Pattern.compile("<body>\\s*O_O([\\s\\S]*)O_O\\s*</body>")
                                .matcher(page);
                if (!m.find())
                        return ret;
                String a = m.group(1);
                StringTokenizer s = new StringTokenizer(a, "|");
                while (s.countTokens() != 0)
                        ret.add(s.nextToken());
                return ret;
        }

        @Override
        public int signIn(String username, String password) throws Exception {
                ArrayList<String> ret = getResponse(
                                "http://wahab.homeip.net:8080/JudgesEngineCore/index.jsp",
                                "username=" + username + "&password=" + password
                                                + "&ID=1&JID=LiveArchive");
                return Integer.parseInt(ret.get(0));
        }

        private ArrayList<String> getVolumes() throws HttpException, IOException {
        ArrayList<String> ret = new ArrayList<String>();
        HttpClient h = new HttpClient();
        for (Integer i = 1; i < 3; i++) {
            GetMethod g = new GetMethod("http://livearchive.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&category=" + i.toString());
            g.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
            h.getParams().setParameter(HttpMethodParams.USER_AGENT, "Linux-Omar");
            h.executeMethod(g);
            String regex = "<td><a href=\"(index[.]php\\?option=com_onlinejudge&)amp;(Itemid=8&)amp;(category=[\\d]+)\">Volume [\\S]+</a></td>";
            Matcher m = Pattern.compile(regex).matcher(g.getResponseBodyAsString());
            while (m.find()) {
                ret.add("http://livearchive.onlinejudge.org/" + m.group(1) + m.group(2) + m.group(3));
            }
        }
        for(int i = 0 ; i < ret.size() ; i ++)
                System.out.println(ret.get(i));
        
        return ret;
        }

        @Override
        public ArrayList<Problem> getAllProblems() throws HttpException,
                        IOException {
                 ArrayList<Problem> ret = new ArrayList<Problem>();
                ArrayList<String> volumes = getVolumes();
                System.out.println("Processing " + volumes.size() + " Volumes ...");
                HttpClient h = new HttpClient();
                PrintWriter p = new PrintWriter(new File("/home/workspace/JudgesEngine/src/ProblemsFiles/LiveArchive.txt"));
                for (int i = 0; i < volumes.size(); i++) {
                    System.out.println("Parsing Volume " + i + " ... ");
                    String regex = "<td><a href=\"(index[.]php\\?option=com_onlinejudge&)amp;(Itemid=8&)amp;(category=\\d+&)amp;(page=show_problem&)amp;(problem=\\d+)\">(\\d+)&nbsp;-&nbsp;([^<]+)</a></td>";
                    GetMethod g = new GetMethod(volumes.get(i));
                    g.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
                    h.getParams().setParameter(HttpMethodParams.USER_AGENT, "Linux-Omar");
                    h.executeMethod(g);
                    Matcher m = Pattern.compile(regex).matcher(g.getResponseBodyAsString());
                    while (m.find()) {
                        ret.add(new Problem(m.group(6), "http://livearchive.onlinejudge.org/" + m.group(1) + m.group(2) + m.group(3) + m.group(4) + m.group(5), m.group(7), null, null));
                        p.write(ret.get(ret.size()-1).getId() + " | " + ret.get(ret.size()-1).getName() + " | " + ret.get(ret.size()-1).getUrl()+"\n");
                    }
                    p.flush();
                }
                p.close();
                return ret;

        }
        /* (non-Javadoc)
         * @see Engine.Judge#getLastSubmission(java.lang.String, java.lang.String)
         */
        @Override
        public Submission getLastSubmission(String coderId, String password,
                        String ids) throws Exception {
                ArrayList<String> a = getResponse(
                                "http://wahab.homeip.net:8080/JudgesEngineCore/index.jsp",
                                "username=" + coderId + "&password=" + password
                                                + "&ID=4&JID=LiveArchive&idl=" + ids);
                if (a.size() != 6)
                        return new Submission();
                return new Submission(a.get(3), a.get(0), a.get(4), "", a.get(5),
                                a.get(1));
        }
        /* (non-Javadoc)
         * @see Engine.Judge#getProblemInfo(java.lang.String)
         */
        @Override
        public Problem getProblemInfo(String problemId) {
                // TODO Auto-generated method stub
                return null;
        }
        
        @Override
        public ArrayList<String> getProblemsSolved(String coderId) {

                return null;
        }

        /*
         * Value --> language 1 --> ANSI C 4.1.2 - GNU C Compiler with options: -lm
         * -lcrypt -O2 -pipe -ansi -DONLINE_JUDGE 2 --> JAVA 1.6.0 - Java Sun JDK 3
         * --> C++ 4.1.2 - GNU C++ Compiler with options: -lm -lcrypt -O2 -pipe
         * -DONLINE_JUDGE 4 --> PASCAL 2.0.4 - Free Pascal Compiler (non-Javadoc)
         * 
         * @see Engine.Judge#submitProblem(java.lang.String, java.lang.String,
         * java.lang.String, java.lang.String, java.lang.String)
         */
        @Override
        public Long submitProblem(String coderId, String password,
                        String problemId, String language, String code) throws Exception {
                char[] chars = { '%', '{', '}', '|', '\\', '^', '~', '[', ']', ';',
                                '/', '?', ':', '@', '=', '&', '$', '+' };
                String[] map = { "%25", "%7B", "%7D", "%7C", "%5C", "%5E", "%7E",
                                "%5B", "%5D", "%3B", "%2F", "%3F", "%3A", "%40", "%3D", "%26",
                                "%24", "%2B" };
                for (int i = 0; i < map.length; i++) {
                        String tem = "";
                        for (int k = 0; k < code.length(); k++) {
                                if (code.charAt(k) == chars[i])
                                        tem += map[i];
                                else
                                        tem += Character.toString(code.charAt(k));
                        }
                        code = tem;
                }
                String ret = getResponse(
                                "http://wahab.homeip.net:8080/JudgesEngineCore/index.jsp",
                                "username=" + coderId + "&password=" + password + "&problemId="
                                                + problemId + "&languageId=" + language + "&code="
                                                + code + "&ID=3&JID=LiveArchive").get(0);
                return Long.parseLong(ret);
        }

        @Override
        public boolean signOut(String username) {
                return true;
        }

        @Override
        public ArrayList<ProblemText> getProblemTexts() throws Exception {
                ArrayList<ProblemText> ret = new ArrayList<ProblemText>();
                GetMethod g = new GetMethod();
                HttpClient h = new HttpClient();
                Scanner s = new Scanner(new File(
                                "/home/workspace/JudgesEngine/src/ProblemsFiles/LiveArchive.txt"));
                String line;
                s.nextLine();
                PrintWriter p = new PrintWriter(new File(
                                "/home/workspace/JudgesEngine/src/ProblemsTextFiles/LiveArchive/LiveArchive1.txt"));
                int f = 0;
                Integer folder = 19 , ID = 2;
                // 3221225472
                Long sz = 0L;
                String dim = "______________________________________________________\n";
                while (s.hasNext()) {
                        line = s.nextLine();
                        Integer id = Integer.parseInt(line.substring(0,
                                        line.indexOf("|") - 1));
                        f ++;
                        if (f % 10 == 0)
                                p.flush();
                        if (id % 100 == 0) 
                                folder++;
                        g = new GetMethod("http://livearchive.onlinejudge.org/external/"
                                        + folder.toString() + "/" + id.toString()+".html");
                        System.out.println("http://livearchive.onlinejudge.org/external/"
                                        + folder.toString() + "/" + id.toString()+".html" +" ------- " + (ID-1));
                        h.executeMethod(g);
                        String gg = g.getResponseBodyAsString() , ff = line.substring(0, line.indexOf("|") - 1)
                        + "||||||false||||||\n";
                        p.write(ff);
                        gg = fixURL(gg,
                                        "http://livearchive.onlinejudge.org/external/" + folder.toString()
                                                        + "/");
                        p.write(gg + "\n");
                        p.write(dim);
                        sz += gg.length()+dim.length()+ff.length()+1;
                        System.err.print(sz + " " );
                        System.out.println(3145728L);
                        if(sz >= 3145728L){
                                sz = 0L;
                                p.flush();
                                p.close();
                                p = new PrintWriter(new File("/home/workspace/JudgesEngine/src/ProblemsTextFiles/LiveArchive/LiveArchive" + ID.toString() + ".txt"));
                                ID ++;
                                System.err.println(dim);
                        }
                        ret.add(new ProblemText("", "", "", "", false, gg));
                        System.out
                                        .println("______________________________________________________");
                }
                p.flush();
                p.close();
                return ret;
        }

        private String fixURL(String r, String url) {
                int i = 0;
                String regex = "(SRC|src)=\"([^\"]+)\"";
                Matcher m = Pattern.compile(regex).matcher(r);
                if (m.find()) {
                        r = r.replaceAll(regex,
                                        m.group(1)+"=\""+ url + m.group(2));
//                      System.out.println(r);
                }
                r = r.replace("https", "http");
                return r;
        }
}