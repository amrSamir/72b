package com.OJToolkit.server.engine;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class SPOJ implements Judge {

        @Override
        public ArrayList<Problem> getAllProblems() throws Exception {
                ArrayList<Problem> ret = new ArrayList<Problem>();
                String[] arr = { "classical", "challenge", "partial", "tutorial" };
                for (int i = 0; i < arr.length; i++) {

                        for (int st = 0;; st += 50) {
                                URL siteUrl = new URL("http://www.spoj.pl/problems/" + arr[i]
                                                + "/sort=0,start=" + st);
                                HttpURLConnection conn = (HttpURLConnection) siteUrl
                                                .openConnection();
                                conn.setRequestMethod("POST");
                                conn.setDoOutput(true);
                                conn.setDoInput(true);
                                BufferedReader in = new BufferedReader(new InputStreamReader(
                                                conn.getInputStream()));
                                String line = "";
                                int nop = 0;
                                while ((line = in.readLine()) != null) {
                                        @SuppressWarnings("unused")
                                        int ind;
                                        if ((ind = line.indexOf("problemrow")) != -1) {
                                                int ii = 0;
                                                Problem p = new Problem();
                                                for (int k = 1; ii < 3; k++) {
                                                        line = in.readLine();
                                                        String tem = "";
                                                        if (k == 3) {
                                                                int in2 = line.indexOf("\"");
                                                                for (int j = in2 + 1; line.charAt(j) != '\"'; j++) {
                                                                        tem += line.charAt(j);
                                                                }
                                                                p.setUrl("https://spoj.pl" + tem);
                                                                int in3 = line.indexOf("<b>");
                                                                tem = "";
                                                                for (int j = in3 + 3; j < line.length()
                                                                                && line.charAt(j) != '<'; j++) {
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
                                                for (int l = 0; l < p.getUrl().length(); l++) {
                                                        if (t == 4 && p.getUrl().charAt(l) != '/') {
                                                                tem += p.getUrl().charAt(l);
                                                        } else if (t > 4) {
                                                                break;
                                                        }
                                                        if (p.getUrl().charAt(l) == '/')
                                                                t++;
                                                }
                                                p.setId(tem);
                                                ret.add(p);
                                        }
                                }
                                System.out.println(ret.size() + " " + i);
                                if (nop != 50)
                                        break;
                        }
                }
                return ret;
        }

        @Override
        public Submission getLastSubmission(String coderId, String pass, String ids)
                        throws Exception {
                Submission ret = new Submission();
                URL siteUrl = new URL("http://www.spoj.pl/status/" + coderId + "/");
                HttpURLConnection conn = (HttpURLConnection) siteUrl.openConnection();
                conn.setRequestMethod("GET");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                out.writeBytes("");
                out.flush();
                out.close();
                BufferedReader in = new BufferedReader(new InputStreamReader(
                                conn.getInputStream()));
                String te;
                StringBuilder s = new StringBuilder();
                while ((te = in.readLine()) != null) {
                        s.append(te + " ");
                }
                conn.disconnect();
                in.close();
                String maxR = "<input type=\"hidden\" id=\"max_id\" value=\"(\\d+)\" />";
                String dateR = "<td class=\"status_sm\">\\s*([\\d|\\:|\\-|\\s]+)\\s*</td>"; // href=\"/problems/";
                String resultR = "<td><a href=\"[\\s\\S]+>\\s*(compilation error|accepted|runtime error    (NZEC)|time limit exceeded|wrong answer)[\\s\\S]*<td class=\"statustext\" id=\"statustime_";
                String timeR = "<td class=\"statustext\" id=\"statustime_\\d+\">\\s+<a href=\"/ranks/[A-Z]+/\" title=\"See the best solutions\">\\s+([\\-|\\d|\\.]+)\\s+</a>";
                String memoryR = "<td class=\"statustext\" id=\"statusmem_\\d+\">\\s+([\\-|\\.|M|\\d|k]+)\\s+</td>";
                String langR = "<td class=\"slang\">\\s*<p>([\\+|A-Z]+)</p>";
                String problemidR = "<td><a href=\"/problems/(\\w+)/\"";
                Submission sub = new Submission("", "", "", "", "", "");
                Matcher m1 = Pattern.compile(maxR).matcher(s);
                m1.find();
                m1 = Pattern.compile(resultR+m1.group(1)).matcher(s);
                if (!m1.find())
                        return sub;
                ret.setStatus(m1.group(1));
                m1 = Pattern.compile(dateR).matcher(s);
                if (!m1.find())
                        return sub;
                ret.setDate(m1.group(1));
                m1 = Pattern.compile(timeR).matcher(s);
                if (!m1.find())
                        return sub;
                ret.setRuntime(m1.group(1));
                m1 = Pattern.compile(memoryR).matcher(s);
                if (!m1.find())
                        return sub;
                ret.setMemoryUsed(m1.group(1));
                m1 = Pattern.compile(langR).matcher(s);
                if (!m1.find())
                        return sub;
                ret.setLanguage(m1.group(1));
                m1 = Pattern.compile(problemidR).matcher(s);
                if (!m1.find())
                        return sub;
                ret.setProblemId(m1.group(1));
                return ret;
        }

        @Override
        public Problem getProblemInfo(String problemId) throws Exception {
                HashMap<String, Integer> temp = new HashMap<String, Integer>();
                String arr[] = { "Users accepted", "Submissions", "Accepted",
                                "Wrong Answer", "Compile Error", "Runtime Error",
                                "Time Limit Exceeded" };
                URL siteUrl = new URL("https://www.spoj.pl/ranks/" + problemId + "/");
                HttpURLConnection conn = (HttpURLConnection) siteUrl.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                BufferedReader in = new BufferedReader(new InputStreamReader(
                                conn.getInputStream()));
                String line = "";
                int ind = 0;
                boolean flag = false;
                String name = "", id = "/problems/" + problemId + "\">";
                while ((line = in.readLine()) != null) {
                        int tem;
                        if ((tem = line.indexOf(id)) != -1) {
                                for (int k = tem + id.length(); line.charAt(k) != '<'; k++)
                                        name += line.charAt(k);
                        }
                        if (line.indexOf("lightrow") != -1)
                                flag = true;
                        else if (flag) {
                                String n = "";
                                for (int i = 0; i < line.length(); i++) {
                                        if (line.charAt(i) >= '0' && line.charAt(i) <= '9')
                                                n += line.charAt(i);
                                }
                                temp.put(arr[ind++], Integer.parseInt(n));
                        }
                        if (ind == arr.length)
                                break;
                }
                conn.disconnect();
                Integer x = (temp.get((Object) "Submissions") - temp
                                .get((Object) "Accepted"));
                Problem ret = new Problem(problemId, "http://www.spoj.pl/problems/"
                                + problemId, name, temp.get((Object) "Accepted").toString(),
                                x.toString());
                return ret;
        }

        @Override
        public ArrayList<String> getProblemsSolved(String coderId) throws Exception {
                URL site = new URL("http://www.spoj.pl/users/" + coderId);
                ArrayList<String> ret = new ArrayList<String>();
                HttpURLConnection conn = (HttpURLConnection) site.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                ;
                out.writeBytes("");
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
                int s, ch = 0, chs = 0;
                String f = "<td align=\"left\" width=\"14%\"><a href=";
                String temp = "", prob = "";
                while ((s = page.indexOf(f)) != -1) {
                        page.setCharAt(s, '&');
                        s += f.length();
                        temp = "";
                        prob = "";
                        ch = chs = 0;
                        boolean flagP = false;
                        for (int k = s; ch != 2; k++) {
                                if (page.charAt(k) == '"') {
                                        ch++;
                                        continue;
                                } else if (page.charAt(k) == '/')
                                        chs++;
                                else if (page.charAt(k) == ',')
                                        flagP = false;

                                temp += page.charAt(k);
                                if (flagP)
                                        prob += page.charAt(k);
                                if (chs == 2) {
                                        flagP = true;
                                        chs = 0;
                                }
                        }
                        if (prob.equals(""))
                                continue;
                        ret.add("https://spoj.pl" + temp);
                }
                return ret;
        }

        @Override
        public Long submitProblem(String coderId, String password,
                        String problemId, String languageId, String code)
                        throws IOException {
                URL siteUrl = new URL("http://www.spoj.pl/submit/complete/");
                HttpURLConnection conn = (HttpURLConnection) siteUrl.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                DataOutputStream out = new DataOutputStream(conn.getOutputStream());
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
                String[] params = { "login_user", "password", "problemcode", "file",
                                "lang", "submit" };
                String[] values = { coderId, password, problemId, code, languageId,
                                "Send" };
                StringBuilder content = new StringBuilder();
                for (int i = 0; i < params.length; i++) {
                        if (i != 0)
                                content.append("&");
                        content.append(params[i] + "=" + values[i]);
                }
                out.writeBytes(content.toString());
                out.flush();
                out.close();
                conn.getInputStream();
                conn.disconnect();
                return null;
        }

        @Override
        public boolean signIn(String username, String password) throws Exception {
                ArrayList<String> ret = getResponse(
                                "http://wahab.homeip.net:8080/JudgesEngineCore/index.jsp",
                                "username=" + username + "&password=" + password
                                                + "&ID=1&JID=SPOJ");
                return Boolean.parseBoolean(ret.get(0));
        }

        @Override
        public boolean signOut(String username) {
                return true;
        }

        private String match(String text, String regex, int g) {
                Matcher m = Pattern.compile(regex).matcher(text);
                if (m.find())
                        return m.group(g);
                return "la2a";
        }

        @Override
        public ArrayList<ProblemText> getProblemTexts()
                        throws Exception {
                ArrayList<ProblemText> ret = new ArrayList<ProblemText>();
                String dis = "<p align=\"justify\">([\\s\\S]*?)(<h3[^<>]*>Input|<hr>)";
                String input = "<h3[^<>]*>Input</h3>([\\s\\S]*?)(<h3[^<>]*>|<hr>)";
                String output = "<h3[^<>]*>Output</h3>([\\s\\S]*?)(<h3[^<>]*>|<hr>)";
                String sampleT = "<h3[^<>]*>Example</h3>([\\s\\S]*?)(<h3[^<>]*>|<hr>)";

                GetMethod g = new GetMethod();
                HttpClient h = new HttpClient();
                Scanner s = new Scanner(new File(""));
                String line;
                s.nextLine();
                PrintWriter p = new PrintWriter(new File(
                                "/home/workspace/JudgesEngine/src/ProblemsTextFiles/SPOJ.txt"));
                int f = 0;
                while (s.hasNext()) {
                        f++;
                        if (f % 10 == 0)
                                p.flush();
                        line = s.nextLine();
                        g = new GetMethod("http://www.spoj.pl/problems/"
                                        + line.substring(0, line.indexOf("|") - 1));
                        h.executeMethod(g);
                        System.out.println(line.substring(0, line.indexOf("|") - 1));
                        String d = match(g.getResponseBodyAsString(), dis, 1);
                        String i = match(g.getResponseBodyAsString(), input, 1);
                        String o = match(g.getResponseBodyAsString(), output, 1);
                        String ss = match(g.getResponseBodyAsString(), sampleT, 1);
                        if (i.equals("la2a") || o.equals("la2a") || ss.equals("la2a")) {
                                p.write(line.substring(0, line.indexOf("|") - 1) + "|false\n");
                                // System.out.println(line.substring(0, line.indexOf("|")-1) +
                                // "|false\n");
                                ret.add(new ProblemText("", "", "", "", false, d));
                                // System.out.println(d);
                                p.write(d + "\n");
                        } else {
                                System.out.println(line.substring(0, line.indexOf("|") - 1)
                                                .length());
                                p.write(line.substring(0, line.indexOf("|") - 1) + "|true\n");
                                // System.out.println(line.substring(0, line.indexOf("|")-1) +
                                // "|true\n");
                                p.write("*******ProblemStatement*******\n" + d
                                                + "\n*******InputConstraints*******\n" + i
                                                + "\n*******OutputConstraints*******\n" + o
                                                + "\n*******IOTestCases*******\n" + ss + "\n");

                                // System.out.println("*******ProblemStatement*******\n" + d +
                                // "\n*******InputConstraints*******\n" + i +
                                // "\n*******OutputConstraints*******\n" + o +
                                // "\n*******IOTestCases*******\n" + ss);
                                ret.add(new ProblemText(d, i, o, ss, true, ""));
                        }
                        // System.out.println("______________________________________________________");
                        p.write("______________________________________________________\n");
                }
                p.flush();
                p.close();
                return ret;
        }

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
                Matcher m = Pattern.compile("<body>\\s*O_O([\\s\\S]*)O_O\\s*</body>")
                                .matcher(page);
                m.find();
                String a = m.group(1);
                StringTokenizer s = new StringTokenizer(a, "|");
                ArrayList<String> ret = new ArrayList<String>();
                while (s.countTokens() != 0)
                        ret.add(s.nextToken());
                return ret;
        }
}
