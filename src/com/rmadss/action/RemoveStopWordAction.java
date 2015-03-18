package com.rmadss.action;

import java.io.IOException;

import java.util.ArrayList;
import java.util.ListIterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;




@SuppressWarnings("serial")
public class RemoveStopWordAction extends HttpServlet {
	
	private static Logger logger = Logger.getLogger(RemoveStopWordAction.class);

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path="";
		
		try{
			
			
			HttpSession session=request.getSession(true);
			
			
			 String[] stopWords = new String[]{" a "," an "," and "," are "," as "," at "," be "," but "," by "," for ",
                     " if "," in "," into "," is "," it "," no "," not "," of "," on "," or "," has "," has "," he "," then ",
                     " s "," such "," t "," that "," the "," their "," then "," there ",
                     " these "," they "," this "," to "," was "," will "," with ","e.g.,"," a ", " about ", " above " , " above " , " across ", " after ", " afterwards ", " again ", " against ", " all ", " almost ", 
			            " alone ", " along ", " already ", " also "," although "," always "," am "," among ", " amongst ", " amoungst ", " amount ",  " an ", " and ", 
			            " another " , " any "," anyhow "," anyone "," anything "," anyway ", " anywhere ", " are ", " around ", " as ",  " at ", " back "," be "," became ", 
			            " because "," become "," becomes ", " becoming ", " been ", " before ", " beforehand ", " behind ", " being ", " below ", " beside ", " besides ", 
			            " is ", " it ", " its ", " itself ", " keep ", "l ast ", " latter ", " latterly ", " least ", " less ", " ltd ", " made ", " many ", 
			            " may ", " me ", " meanwhile ", " might ", " mill ", " min e", " more ", " moreover ", " most ", " mostly ", " move ", " much ", " must ", 
			            " my ", " myself ", " name ", " namely ", "neither ", " never ", " nevertheless ", " next ", " nine ", " no ", " nobody ", " none ", 
			            " noone ", " nor ", " not ", " nothing ", " now ", " nowhere ", " of ", " off ", " often ", " on " , " once ", " one ", " only ", " onto ",    
			            " contact "," grounds "," buyers "," tried "," said "," plan "," value "," principle. "," forces "," sent: "," is, "," was "," like ",
			            " discussion "," tmus "," diffrent. "," layout "," area. "," thanks "," thankyou "," hello "," bye "," rise "," fell "," fall "," psqft. "," http:// "," km "," miles "};

			
			
			 String filedata=(String) session.getAttribute("str2");
			 
			   System.out.println("in remove stop action class "+filedata);	
			 
			   for (String stopword : stopWords) {
		            filedata = filedata.replaceAll("(?i)"+stopword," ");
		        }
			 
			   
			   System.out.println("after removal of stopwords"+filedata );
			   
			   
			   session.setAttribute("stopwordfiledata",filedata);
			   
			   
				path="./AfterRemoveStopWord.jsp";
				request.setAttribute("status", " After removing stop words");
			
		}catch (Exception e) {
			logger.error(e);
			logger.info(e);
			path="./userhome.jsp";
			request.setAttribute("status", " Plz try again");
		}
		RequestDispatcher rd=request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

}
