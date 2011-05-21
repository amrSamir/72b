package com.OJToolkit.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.OJToolkit.client.ValueObjects.ProblemData;

public class Engine {

	private static final Logger LOG = Logger.getLogger(Engine.class.getName());

	/*
	 * TODO: 1- uvaSubmit 3- getUvaUserInfo 5- getProblemStatus_Uva
	 */

	/**
	 * 
	 * @param HashMap
	 *            <String,String> data
	 * 
	 *            KEY VALUE -------- login_user --> user name. -------- password
	 *            --> user's password. -------- problemcode --> problem code/id.
	 *            -------- file --> submitted code. -------- lang --> language
	 *            id , and it must be like the spoj's one.
	 * 
	 * @return void
	 * 
	 * @throws Exception
	 */
	public static void spojSubmit(HashMap<String, String> data)
			throws Exception {
		URL siteUrl = new URL("http://www.spoj.pl/submit/complete/");
		HttpURLConnection conn = (HttpURLConnection) siteUrl.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.setDoInput(true);
		// conn.setRequestProperty("Content-type", "text/xml; charset=" +
		// "UTF-8");
		DataOutputStream out = new DataOutputStream(conn.getOutputStream());
		Set keys = data.keySet();
		Iterator keyIter = keys.iterator();
		String content = "";
		for (int i = 0; keyIter.hasNext(); i++) {
			Object key = keyIter.next();
			if (i != 0)
				content += "&";
			content += key + "=" + URLEncoder.encode(data.get(key), "UTF-8");
		}
		out.writeBytes(content);
		out.flush();
		out.close();
		conn.getInputStream();
		conn.disconnect();
	}

	/**
	 * 
	 * @param HashMap
	 *            <String , String> data
	 * 
	 *            KEY VALUE -------- login_user --> user name. -------- password
	 *            --> user's password.
	 * 
	 * @return HashMap<String , String> :
	 * 
	 *         1- All the solved problems , the key is the code/id of the
	 *         problem. 2- All the info of the user's page , and the keys are:
	 *         "Problems solved" "Solutions submitted" "Solutions accepted"
	 *         "Wrong Answer" "Compile Error" "Runtime Error" ,
	 *         "Time Limit Exceeded"
	 * 
	 * @throws Exception
	 */
	public static HashMap<String, String> getSpojUserInfo(
			HashMap<String, String> data) throws Exception {
		URL site = new URL("http://www.spoj.pl/myaccount/");
		HashMap<String, String> ret = new HashMap<String, String>();
		HttpURLConnection conn = (HttpURLConnection) site.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.setDoInput(true);
		DataOutputStream out = new DataOutputStream(conn.getOutputStream());
		Set keys = data.keySet();
		Iterator keyIter = keys.iterator();
		String content = "";
		for (int i = 0; keyIter.hasNext(); i++) {
			Object key = keyIter.next();
			if (i != 0)
				content += "&";
			content += key + "=" + data.get(key);
		}
		out.writeBytes(content);
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
			ret.put(prob, "https://spoj.pl" + temp);
		}
		f = "<td>Problems solved</td>\n<td>Solutions submitted</td>\n<td>Solutions accepted</td>\n<td>Wrong Answer</td>\n<td>Compile Error</td>\n<td>Runtime Error</td>\n<td>Time Limit Exceeded</td>\n</tr>\n<tr class=\"lightrow\">";
		s = page.indexOf(f) + f.length();
		String[] arr = { "Problems solved", "Solutions submitted",
				"Solutions accepted", "Wrong Answer", "Compile Error",
				"Runtime Error", "Time Limit Exceeded" };
		ch = 0;
		temp = "";
		for (int i = s; ch < arr.length; i++) {
			if (page.charAt(i) >= '0' && page.charAt(i) <= '9')
				temp += page.charAt(i);
			else {
				if (temp.length() != 0)
					ret.put(arr[ch++], temp);
				temp = "";
			}
		}
		return ret;
	}

	/**
	 * 
	 * @param HashMap
	 *            <String , String>data
	 * 
	 *            KEY VALUE -------- login_user --> user name.
	 * 
	 * @return HashMap<String , String>
	 * 
	 *         KEY VALUE -------- DATE --> Date and time for the submitted
	 *         problem. -------- PROBLEM --> Link of the problem submitted.
	 *         -------- RESULT --> The response of the judge. -------- TIME -->
	 *         The running time of the solution. -------- MEM --> The memory
	 *         used by the solution.
	 * 
	 * @throws Exception
	 */
	public static HashMap<String, String> getLastProblemStatus_Spoj(
			HashMap<String, String> data) throws Exception {
		HashMap<String, String> ret = new HashMap<String, String>();
		URL siteUrl = new URL("http://www.spoj.pl/status/"
				+ data.get("login_user") + "/");
		HttpURLConnection conn = (HttpURLConnection) siteUrl.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.setDoInput(true);
		DataOutputStream out = new DataOutputStream(conn.getOutputStream());
		Set keys = data.keySet();
		Iterator keyIter = keys.iterator();
		String content = "";
		for (int i = 0; keyIter.hasNext(); i++) {
			Object key = keyIter.next();
			if (i != 0)
				content += "&";
			content += key + "=" + URLEncoder.encode(data.get(key), "UTF-8");
		}
		out.writeBytes(content);
		out.flush();
		out.close();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				conn.getInputStream()));
		String tem, teto = "<td class=\"status_sm\">";
		int t = 0;
		String[] arr = { "DATE", "PROBLEM", "RESULT", "TIME", "MEM" };
		int ind = 0, ln = 1, temp;
		boolean flag = false;
		while ((tem = in.readLine()) != null && ind != arr.length) {

			if ((temp = tem.indexOf(teto)) != -1) {
				flag = true;
			}
			if (flag) {
				if (ln == 3) { // For The Date.
					ret.put(arr[ind++], tem);
				} else if (ln == 10) { // For the Result
					String s = "";
					for (int i = 0; i < tem.length(); i++)
						if ((tem.charAt(i) >= 'a' && tem.charAt(i) <= 'z')
								|| tem.charAt(i) == ' ')
							s += tem.charAt(i);
					ret.put(arr[ind++], s);
					if (tem.equals("compilation error"))
						break;
				} else if (ln == 6) { // For the problem's link.
					String s = "";
					int ch = 0;
					for (int i = 13; ch != 3; i++) {
						if (tem.charAt(i) == '/')
							ch++;
						s += tem.charAt(i);
					}
					ret.put(arr[ind++], "https://spoj.pl" + s);
				} else if (ln == 19) { // For the running time.
					String s = "";
					for (int i = 0; i < tem.length(); i++) {
						if (tem.charAt(i) == '.'
								|| (tem.charAt(i) >= '0' && tem.charAt(i) <= '9'))
							s += tem.charAt(i);
					}
					ret.put(arr[ind++], s);
				} else if (ln == 22) { // For the memory used.
					String s = "";
					for (int i = 0; i < tem.length(); i++)
						if ((tem.charAt(i) >= '0' && tem.charAt(i) <= '9')
								|| tem.charAt(i) == '.' || tem.charAt(i) == 'M')
							s += tem.charAt(i);
					ret.put(arr[ind++], s);
				}
				ln++;
			}
		}
		in.close();
		conn.disconnect();
		return ret;
	}

	public static ArrayList<ProblemData> getAllProblemsSpoj()
			throws IOException {
		ArrayList<ProblemData> ret = new ArrayList<ProblemData>();
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
					int ind;
					if ((ind = line.indexOf("problemrow")) != -1) {
						int ii = 0;
						ProblemData p = new ProblemData();
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
								p.setProblemName(tem);
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
						p.setProblemCode(tem);
						System.out.println("Problem URL " + p.getUrl()
								+ " Problem Code " + p.getProblemCode()
								+ " Problem Title " + p.getProblemName());
						// LOG.log(Level.SEVERE, "Problem URL " + p.getUrl() +
						// " Problem Code " + p.getProblemCode() +
						// " Problem Title " + p.getProblemName());
						ret.add(p);
					}
				}
				// System.out.println(st + "|" + ret.size());
				if (nop != 50)
					break;
			}
		}
		return ret;
	}

}
