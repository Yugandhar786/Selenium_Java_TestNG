package com.Orange.utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class ExcelReadAndWriteUsingFillo {

	static HashMap<String, String> givenRowValues = new HashMap<String, String>();
	static String dataSheet = ".\\src\\test\\resources\\Files\\Test Data.xlsx";
	static String updateSheet = ".\\src\\test\\resources\\Files\\Another Excel.xlsx";

	public static HashMap<String, String> getSpecificRow(int TCNo) throws FilloException {
		Fillo fillo = new Fillo();
		Connection connection = fillo.getConnection(dataSheet);
		String query = "Select * from Sheet1 where TCNo = " + TCNo;
		Recordset recordSet = connection.executeQuery(query);

		while (recordSet.next()) {
			ArrayList<String> columnHeaders = recordSet.getFieldNames();
			for (int i = 0; i < columnHeaders.size(); i++) {
				String columnHeader = columnHeaders.get(i);
				String columnValues = recordSet.getField(columnHeaders.get(i));
				if (columnValues.equalsIgnoreCase("NA")) {
					givenRowValues.put(columnHeader, columnValues);
				}
			}
		}
		System.out.println(givenRowValues);
		return givenRowValues;
	}

	public static String getFieldValue(String fieldName) {

		Set<Map.Entry<String, String>> map = givenRowValues.entrySet();
		Iterator<Entry<String, String>> ite = map.iterator();
		String value = "";
		while (ite.hasNext()) {
			Entry<String, String> entry = ite.next();
			if (entry.getKey().equals(fieldName)) {
				System.out.println(entry.getValue());
				value = entry.getValue();
			}
		}
		return value;
	}

	public static void WriteOnExcel() throws Exception {
		ReadAndWriteProperty prop = new ReadAndWriteProperty();
		String name = prop.readAProperty(ReadAndWriteProperty.propFileToStoreData, "FirstName");
		String age = prop.readAProperty(ReadAndWriteProperty.propFileToStoreData, "Age");

		Fillo fillo = new Fillo();
		Connection connection = fillo.getConnection(updateSheet);

		String query = "INSERT INTO Sheet1 (\"First Name\", \"Age\") VALUES ('" + name + "', " + age + "')";
		System.out.println(query);
		connection.executeQuery(query);

	}

}
