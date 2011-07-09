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
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.httpclient.HttpException;

public class UVA implements Judge {

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
                conn.setReadTimeout(0);
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
                if(!m.find())
                        return ret;
                String a = m.group(1);
                StringTokenizer s = new StringTokenizer(a, "|");
                while (s.countTokens() != 0)
                        ret.add(s.nextToken());
                return ret;
        }

        @Override
        public boolean signIn(String username, String password) throws Exception {
                ArrayList<String> ret = getResponse(
                                "http://wahab.homeip.net:8080/JudgesEngineCore/index.jsp",
                                "username=" + username + "&password=" + password
                                                + "&ID=1&JID=UVA");
                return Boolean.parseBoolean(ret.get(0));
        }

        private ArrayList<String> getVolumes() throws HttpException, IOException {
                throw new UnsupportedOperationException(
                                "This function not implemented yet!");

        }

        @Override
        public ArrayList<Problem> getAllProblems() throws HttpException,
                        IOException {
                throw new UnsupportedOperationException(
                                "This function not implemented yet!");

        }

        @Override
        public Submission getLastSubmission(String coderId, String password)
                        throws Exception {
                ArrayList<String> a = getResponse(
                                "http://wahab.homeip.net:8080/JudgesEngineCore/index.jsp",
                                "username=" + coderId + "&password=" + password
                                                + "&ID=4&JID=UVA");
                return new Submission(a.get(3), a.get(0), a.get(4), "", a.get(5),
                                a.get(1));
        }

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
         * Value --> language
         *  1 --> ANSI C 4.1.2 - GNU C Compiler with options: -lm  -lcrypt -O2 -pipe -ansi -DONLINE_JUDGE 
         *  2 --> JAVA 1.6.0 - Java Sun JDK 
         *  3 --> C++ 4.1.2 - GNU C++ Compiler with options: -lm -lcrypt -O2 -pipe -DONLINE_JUDGE 
         *  4 --> PASCAL 2.0.4 - Free Pascal Compiler (non-Javadoc)
         * @see Engine.Judge#submitProblem(java.lang.String, java.lang.String,
         * java.lang.String, java.lang.String, java.lang.String)
         */
        @Override
        public void submitProblem(String coderId, String password,
                        String problemId, String language, String code) throws Exception {
                char[] chars = { '%', '{', '}', 
                                '|', '\\', '^',
                                '~', '[', ']', 
                                ';', '/', 
                                '?', ':', '@', 
                                '=', '&', '$', '+' };
                String[] map = { "%25", "%7B", "%7D",
                                "%7C", "%5C", "%5E", 
                                "%7E", "%5B", "%5D", 
                                "%3B", "%2F",
                                "%3F", "%3A", "%40", 
                                "%3D", "%26", "%24", "%2B" };
                for(int i = 0 ; i < map.length ; i ++){
                        String tem = "";
                        for(int k = 0 ; k < code.length() ; k ++){
                                if(code.charAt(k) == chars[i])
                                        tem += map[i];
                                else
                                        tem += Character.toString(code.charAt(k));
                        }
                        code = tem;
                }
                
                getResponse("http://wahab.homeip.net:8080/JudgesEngineCore/index.jsp",
                                "coderId=" + coderId + "&password=" + password + "&problemId="
                                                + problemId + "&languageId=" + language + "&code=" + code + "&ID=3&JID=UVA");
        }

        @Override
        public boolean signOut(String username) {
                return true;
        }

        @Override
        public ArrayList<ProblemText> getProblemTexts(String filePath)
                        throws Exception {
                throw new UnsupportedOperationException(
                                "This function not implemented yet!");
        }
}
