package fr.apoprojetdegut.main.extensions.SaveExcel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import fr.apoprojetdegut.main.Main;
import fr.apoprojetdegut.main.dynamique.Election;
import fr.apoprojetdegut.main.personne.Candidat;
/**
 * Classe SaveFile
 * @author jdegu
 */
public class SaveFile {
	/**
	 * Méthode de classe permettant de prendre les données à sauvegarder et les écrit sur le fichier excel
	 * @param el
	 * @throws IOException
	 */
	public static void save(Election el) throws IOException {
        XSSFWorkbook excel = new XSSFWorkbook();
        ArrayList<ArrayList<Candidat>> data = el.getSaveV().getData();
        ArrayList<ArrayList<Double>> dataP = el.getSaveS().getPercentages();
    	XSSFSheet sheet = excel.createSheet("Election N°1");
    	
    	XSSFRow row = sheet.createRow(0);
    	XSSFCell cell = row.createCell(0); cell.setCellValue("Nb Electeurs");
    	cell = row.createCell(1); cell.setCellValue(Main.listElecteur.size());
    	
    	row = sheet.createRow(1);
    	cell = row.createCell(0); cell.setCellValue("Candidats");
    	for(int j=0;j<Main.listCandidat.size();j++) {
    		cell = row.createCell(j+1); cell.setCellValue(Main.listCandidat.get(j).getNom());
    	}
    	row = sheet.createRow(2);
    	cell = row.createCell(0); cell.setCellValue("Nb Sondages");
    	cell = row.createCell(1); cell.setCellValue(el.getNbSondage());
    	
    	row = sheet.createRow(3);
    	cell = row.createCell(0); cell.setCellValue("Candidat élu");
    	cell = row.createCell(1); cell.setCellValue(el.getFin().getNom());

    	row = sheet.createRow(5);
    	cell = row.createCell(1); cell.setCellValue("Id Electeur");
    	for(int j=0;j<data.size();j++) {
    		cell = row.createCell(j+2); cell.setCellValue(j);
    	}
    	
    	row = sheet.createRow(6);
    	cell = row.createCell(0); cell.setCellValue("Itération");
    	for(int k=0;k<data.size();k++) {
    		row = sheet.createRow(k+7);
    		cell = row.createCell(0); cell.setCellValue(k+1);
    		for(int l=0;l<data.size();l++) {
    			cell = row.createCell(l+2);
    			if(data.get(k).get(l) != null) {
    				cell.setCellValue(data.get(k).get(l).getNom());
    			} else {
       			 	cell.setCellValue("NULL");
    			}
    		}
    	}
    	
    	for(int j=0;j<dataP.size();j++) {
    		row = sheet.createRow(4*j+data.size()+8);
    		cell = row.createCell(0); cell.setCellValue("Sondage n°" + (j+1));
    		row = sheet.createRow(4*j+data.size()+9);
    		cell = row.createCell(0); cell.setCellValue("Candidats");
    		for(Candidat c : Main.listCandidat) {
    			cell = row.createCell(Main.listCandidat.indexOf(c)+1); cell.setCellValue(c.getNom());
    		}
    		cell = row.createCell(Main.listCandidat.size()+1); cell.setCellValue("Abstention");
    		row = sheet.createRow(4*j+data.size()+10);
    		cell = row.createCell(0); cell.setCellValue("Percentage");
    		for(int k=0;k<dataP.get(j).size();k++) {
    			cell = row.createCell(k+1); cell.setCellValue(dataP.get(j).get(k));
    		}
    	}

        try (FileOutputStream outputStream = new FileOutputStream(".\\data\\save.xlsx")) {
            excel.write(outputStream);
            outputStream.close();
        }
    }
 
}
