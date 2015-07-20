package controllers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import models.FacultyPersonnel;
import models.TimeSlot;

public class ReportController {
	private EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("RS_Project");
	private EntityManager em = emfactory.createEntityManager();
	
	float hoursV = 0;
	float hoursP = 0;
	int currentYear = Calendar.getInstance().get(Calendar.YEAR);
	int currentMonth = Calendar.getInstance().get(Calendar.MONTH);

	public void generateReport() {

		System.out.println("Predmeti " + this.findCoursesForUser());

		

		String semestar = (currentMonth > 2 && currentMonth < 10) ? "ljetnji" : "zimski";
		String text = "<div style=\"clear:both;width:85%;margin:auto;\">"
				+ "<div style=\"float:left;width=300px;font-size:1.1em\">" + "<div>UNIVERZITET U TUZLI </div>"
				+ "<div>FAKULTET: Fakultet Elektrotehnike</div>" + "<div>STUDIJSKI PROGRAM: "
				+ AppMain.getUser().getDepartment().getName() + "</div>" + "<div>IZVRŠILAC: " + AppMain.getUser().getNameAndTitle()
				+ "</div>" + "</div>" +

		"<div style=\"float:right;font-weight:bold;font-size:1.1em\">REDOVNI STUDIJ</div>" + "</div>" +

		"<br>" + "<br>" + "<br>" + "<br>" + "<br>" + "<br>" +

		"<div style=\"text-align:center;clear:both;font-size:1.3em;\">IZVJESTAJ O ODRZANOJ NASTAVI/VJEZBAMA</div>" +

		"<br>" + "<br>" +

		"<div style=\"text-align:center;\"><span>za mjesec " + currentMonth + " </span><span> " + semestar
				+ " semestar ak.<span> " + currentYear + " godine</span></div>" +

		"<br>" + "<br>" +

		"<div>" +

		"<style>" + "table, th, td {" + "border: 1px solid black;" + "border-collapse: collapse;" + "}" + "th, td {"
				+ "padding: 0px;" + "margin: 0px;" + "}" + "td {" + "padding: 2px;" +

		"}" +

		"table th:nth-child(5) {" + "width: 70px;" + "}" +

		"table td:nth-child(5)," + "table td:nth-child(6)," + "table tr:last-child td {" + "width:35px;"
				+ "text-align: center;" +

		"}" +

		"th div div {" + "width: 50%;" + "text-align:center;" + "float:left;" + "}" +

		"</style>" +

		"<table border=\"2\" style=\"width:90%; margin:auto;\">" + "<!--header -->" + "<th>Nastavni predmet(i):</th>"
				+ "<th>Datum</th>" + "<th>Mjesto i vrijeme" + "<div>odrzavanja</div>"
				+ "<div>predavanja/vjezbi</div></th>" + "<th>Broj" + "<div>prisutnih</div>" + "<div>studenata</div>"
				+ "</th>" + "<th colspan=\"2\">Broj" + "<div>casova</div>"
				+ "<div style=\"border-top: 1px solid black;text-align:center;\"> " + "<div>P</div>"
				+ "<div style=\"border-left:1px solid;margin-right: -3px;\">V</div>" + "</div>" + "</th>";
		
		
		String content = getContent();
		
		
		
		String footer = "<!--footer -->" + "<tr>" +

		"<th colspan=\"4\" style=\"text-align:right\">Ukupno sati:&nbsp &nbsp &nbsp </th>" + "<td>" + hoursP + "</td>" + hoursV + "<td>5</td>"
				+ "</tr>" + "</table>" +

		"</div>" +

		"<br>" + "<br>" + "<br>" +

		"<div style=\"width:85%;margin:auto;\">" + "<div style=\"float:left;width=300px;\">" + "Izvrsilac:" + AppMain.getUser().getNameAndTitle() + "</div>" +

		"<div style=\"float:right\"> " + "Prodekan za nastavu:" + this.getDean().getNameAndTitle() + "<br>" + "<br>" + "<br>" + "Dekan:" + "</div>"
				+ "</div>";
		try {

			BufferedWriter out = new BufferedWriter(new FileWriter("form.html"));
			out.write(text + content + footer);
			out.close();
		} catch (IOException e) {
			System.out.println("Exception ");
		}

		return;
	}

	private String getContent() {
		String content = "<!--content -->";
		List<TimeSlot> listT = findCoursesForUser();
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		calendar.set(currentYear, currentMonth - 1, 0);
		int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		for(int i = 0; i < daysInMonth; i++) {
			int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
			for(TimeSlot t : listT) {
				System.out.println("Termini out" + t);
				if(t.getDay() == dayOfWeek) {
				System.out.println("Termini " + t);
					float pTime = 0;
					float vTime = 0;
					if(t.getGroup().getType().equals("P")) {
						pTime = t.getLength();
					} else {
						vTime = t.getLength();
					}
					content += "<tr><td>"+AppMain.getUser().getNameAndTitle() +"</td>" 
							+ "<td>"+ format1.format(calendar.getTime()) +"</td>" 
							+ "<td>"+ t.getRoom().toString() +"</td>"
							+ "<td></td>" 
							+ "<td>" + pTime + "</td>" 
							+ "<td>"+ vTime +"</td></tr>";
					
					hoursV += vTime;
					hoursP += pTime;
					
					System.out.println("Termini in" + t);
					
				}
			}
			calendar.set(currentYear, currentMonth - 1, i);
		}
		content +=  "<tr>" + "<td></td>" + "<td></td>" + "<td></td>"
				+ "<td></td>" + "<td></td>" + "<td></td>" + "</tr>";
		return content;
	}

	private List<TimeSlot> findCoursesForUser() {
		TypedQuery<TimeSlot> query = em.createQuery(
				"SELECT e FROM TimeSlot e JOIN e.group gr JOIN gr.instructor instr JOIN gr.course co WHERE instr = :user",
				TimeSlot.class);
		query.setParameter("user", AppMain.getUser());
		List<TimeSlot> bList = query.getResultList();
		// ObservableList<Course> b = FXCollections.observableArrayList(bList);
		return bList;
	}
	
	private FacultyPersonnel getDean() {
		return em.find(FacultyPersonnel.class, 1);
	}

}
