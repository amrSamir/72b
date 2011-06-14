/**
 * 
 */
package com.OJToolkit.server.engine;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;


/**
 * @author 72B
 * Jun 7, 2011
 */
public class UVA implements Judge {
	


    private void signIn(String username, String password, HttpClient c)
                    throws Exception {
            PostMethod p = new PostMethod(
                            "http://uva.onlinejudge.org/index.php?option=com_comprofiler&task=login");
            p.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                            new DefaultHttpMethodRetryHandler());
            c.executeMethod(p);
            String tem = p.getResponseBodyAsString();
            p = new PostMethod("http://uva.onlinejudge.org/index.php?option=com_comprofiler&task=login");
            String regex = "<input type=\"hidden\" name=\"([\\s\\S]*?)\" value=\"([\\s\\S]*?)\" />";
            Matcher m = Pattern.compile(regex).matcher(tem);
            for(int i = 0 ; i < 9 ; i ++)
            {
                    m.find();
                    if(i != 0)
                            p.addParameter(m.group(1), m.group(2));
            }
            p.addParameter("username", username);
            p.addParameter("passwd", password);
            p.addParameter("remember", "yes");
            p.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                            new DefaultHttpMethodRetryHandler());
            c.getParams()
                            .setParameter(
                                            HttpMethodParams.USER_AGENT,
                                            "Linux-Omar");
            System.out.println("login...");
            int code = c.executeMethod(p);
            if (code == 301)
                    System.out.println("Signed In");
            else
                    System.out.println("ERROR");
    }

    private ArrayList<String> getVolumes() throws HttpException, IOException
    {
            ArrayList<String> ret = new ArrayList<String>();
            HttpClient h = new HttpClient();
            for(Integer i = 1 ; i < 3 ; i ++){
                    GetMethod g = new GetMethod("http://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&category="+i.toString());
                    g.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
                    h.getParams().setParameter(HttpMethodParams.USER_AGENT  , "Linux-Omar");
                    h.executeMethod(g);
                    String regex = "<td><a href=\"(index[.]php\\?option=com_onlinejudge&)amp;(Itemid=8&)amp;(category=[\\d]+)\">Volume [\\S]+</a></td>";
                    Matcher m = Pattern.compile(regex).matcher(g.getResponseBodyAsString());
                    while(m.find())
                            ret.add("http://uva.onlinejudge.org/"+m.group(1)+m.group(2)+m.group(3));
            }
            return ret;
    }
    @Override
    public ArrayList<Problem> getAllProblems() throws HttpException,
                    IOException {
            ArrayList<Problem> ret = new ArrayList<Problem>();
            ArrayList<String> volumes = getVolumes();
            System.out.println("Processing "+volumes.size() +" Volumes ...");
            HttpClient h = new HttpClient();
            for(int i = 0 ; i < volumes.size() ; i ++){
                    System.out.println("Parsing Volume " + i + " ... ");
                    String regex = "<td><a href=\"(index[.]php\\?option=com_onlinejudge&)amp;(Itemid=8&)amp;(category=\\d+&)amp;(page=show_problem&)amp;(problem=\\d+)\">(\\d+)&nbsp;-&nbsp;([^<]+)</a></td>";
                    GetMethod g = new GetMethod(volumes.get(i));
                    g.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
                    h.getParams().setParameter(HttpMethodParams.USER_AGENT  , "Linux-Omar");
                    h.executeMethod(g);
                    Matcher m = Pattern.compile(regex).matcher(g.getResponseBodyAsString());
                    while(m.find())
                            ret.add(new Problem(m.group(6), "http://uva.onlinejudge.org/"+m.group(1)+m.group(2)+m.group(3)+m.group(4)+m.group(5), m.group(7), null, null));        
            }
            return ret;
    }

    @Override
    public Submission getLastSubmission(String coderId, String password)
                    throws Exception {
            Submission ret = new Submission();
            HttpClient h = new HttpClient();
            signIn(coderId, password, h);
            GetMethod g = new GetMethod("http://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=9");
            h.executeMethod(g);
            String site = g.getResponseBodyAsString() ;
//          f = "<td align=\"right\">";
//          int ind = site.indexOf(f);
            String id = "<td align=\"right\"><a href=\"[\\s\\S]*?\">([\\d]+)</a></td>"; // 1
            String status = "<td>(\\s+)?(<a href=\"\\S*\">)?(Compilation error|Accepted|Wrong answer|Runtime error|Presentation error|Time limit exceeded)?(</a> )?</td>";
            String language = "<td>(C\\+\\+|JAVA|ANSI C|PASCAL)</td>";
            String runtime = "<td>([\\d]+[.][\\d]+)</td>";
            String date = "<td>([\\d]+-[\\d]+-[\\d]+ [\\d]+:[\\d]+:[\\d]+)</td>";
            String[] arr = {id , date , runtime , status , language};
            int [] val = {1 , 1 , 1 , 3 , 1};
            Method[] tem = ret.getClass().getDeclaredMethods();
            for(int i = 0 , k = 0; i < tem.length ; i ++)
            {
                    if(tem[i].getReturnType().equals(String.class) || tem[i].getName().equals("setMemoryUsed"))continue;
                    Matcher m1 = Pattern.compile(arr[k]).matcher(site);
                    m1.find();
                    tem[i].invoke(ret, m1.group(val[k++]));
            }
            //System.out.println(ret.getDate() + " | " + ret.getLanguage()  + " | " + ret.getProblemId() + " | " + ret.getRuntime() + " | " + ret.getStatus());
            return ret;
    }

    @Override
    public Problem getProblemInfo(String problemId) {
            // TODO remove the null
            return null;
    }

    @Override
    public ArrayList<String> getProblemsSolved(String coderId) {
    		//TODO remove the null
            return null;
    }

    /*
     * Value --> language
  1 --> ANSI C
  2 --> JAVA 1.6.0
  3 --> C++ 4.1.2
  4 --> PASCAL 2.0.4
     * @see Engine.Judge#submitProblem(java.lang.String, java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void submitProblem(String coderId, String password,
                    String problemId, String language, String code) {
            HttpClient c = new HttpClient();
            String[] params = { "localid", "language", "code", "problemid",
                            "category" , "codeupl"};
            String[] values = { problemId, language, code, "", "" , ""};
            PostMethod p = new PostMethod(
                            "http://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=25&page=save_submission");
            try {
	            signIn(coderId, password, c);
            } catch (Exception e) {
	            e.printStackTrace();
            }
            for (int i = 0; i < params.length; i++) {
                    p.addParameter(params[i], values[i]);
            }
            p.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                            new DefaultHttpMethodRetryHandler());
            c.getParams().setContentCharset("UTF-8");
            int cc;
            try {
	            cc = c.executeMethod(p);
	            if (cc == 301)
                    System.out.println("Submitted successfully");
            } catch (HttpException e) {
	            e.printStackTrace();
            } catch (IOException e) {
	            e.printStackTrace();
            }
        
    }

}

