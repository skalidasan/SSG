package com.sennovate.ssg.ad.reports;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.*;

import java.util.Collections;
import java.util.Date;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Scanner;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class AccountDisabled {
	
//	static String businesscategory;
//	static String businessroles;
	static String cn;
//	static String company;
//	static String displayname;
//	static String distinguishedname;
//	static String division;
//	static String employeeid;
//	static String givenname;
//	static String mail;
//	static String manager;
//	static String name;
//	static String sn;
//	static String title;
//	static String memberof;
//	static String employeetype;
//	static String accountexpires;
//	static String sAMAccountName;
	
	public static void main(String[] args) {
		
		Properties prop = new Properties();
		InputStream input = null;
		String initialContextFactory = "";
		String providerURL = "";
		String securityAuthentication = "";
		String securityPrincipal = "";
		String securityCredentials = "";

		try {

			input = new FileInputStream("config.properties");

			prop.load(input);

			initialContextFactory = prop.getProperty("initial_context_factory");
			providerURL = prop.getProperty("provider_url");
			securityAuthentication = prop.getProperty("security_authentication");
			securityPrincipal = prop.getProperty("security_principal");
			securityCredentials = prop.getProperty("security_credentials");

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY, initialContextFactory);
		env.put(Context.PROVIDER_URL, providerURL);
		env.put(Context.SECURITY_AUTHENTICATION, securityAuthentication);
		env.put(Context.SECURITY_PRINCIPAL, securityPrincipal);
		env.put(Context.SECURITY_CREDENTIALS, securityCredentials);

		DirContext ctx = null;
		NamingEnumeration results = null;
		NamingEnumeration results1 = null;
		
		 
		try
		{
			//Scanner a = new Scanner(System.in);
			//System.out.println("Enter a Date of Format (mm/dd/yyyy):");
		    //String s = a.nextLine();
			String s = new String("09/15/2014");
			long date = (new Date(s).getTime() + 11644473600000L) * 10000L;
			//System.out.println(date);
			
			int k = 0;
			ctx = new InitialDirContext(env);
			SearchControls controls = new SearchControls();
			controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
			results = ctx.search("",
									"(&(objectCategory=computer)(lastLogonTimestamp<="+date+"))",
									controls);
			results1 = ctx.search("",
									"(&(objectCategory=computer)(lastLogonTimestamp<="+date+"))",
									controls);
			int i = 0;
			int j = Collections.list(results1).size();
			System.out.println("----------------------------------------");
			while (i < j)
			{
			    SearchResult searchResult = (SearchResult) results.next();
				Attributes attributes = searchResult.getAttributes();
				Attribute attr = attributes.get("cn");
				i++;
				k++;
				if (attributes.get("cn") != null) 
				{
				    cn = attributes.get("cn").toString();
					cn = cn.replace("cn: ", "");
				} 
				else 
				{
					cn = "";
				}
						
	    		System.out.println("----------------------------------------");
				System.out.println("cn : " + cn);
				System.out.println("----------------------------------------");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (results != null) {
				try {
					results.close();
				} catch (Exception e)
				 {
				}
			}
			if (ctx != null) {
				try {
					ctx.close();
				} catch (Exception e) {
				}
			}
		}

	}

}