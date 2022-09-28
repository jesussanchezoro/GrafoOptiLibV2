package grafo.optilib.results;

import grafo.optilib.results.Result.ResultInfo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class TableCreator {

	public static void createTable(String outputFile, List<Result> results) {
		try (PrintWriter pw = new PrintWriter(outputFile)) {
			for (ResultInfo info : results.get(0).getResults()) {
				pw.print(info.getName()+"\t");
			}
			pw.println();
			for (Result r : results) {
				for (ResultInfo info : r.getResults()) {
					pw.print(info.getValue()+"\t");
				}
				pw.println();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//	public static void createTable(String outputFile, List<Result> results) {
//
//		XSSFWorkbook wb = new XSSFWorkbook();
//		XSSFSheet s = wb.createSheet("DATA");
//		// ROW FOR HEADINGS
//		XSSFRow row = s.createRow(0);
//		XSSFCell cell;
//		int columnIdx = 0;
//		cell = row.createCell(columnIdx);
//		cell.setCellValue("Instance");
//		columnIdx++;
//		for (ResultInfo info : results.get(0).getResults()) {
//			cell = row.createCell(columnIdx);
//			cell.setCellValue(info.getName());
//			columnIdx++;
//		}
//		int rowIdx = 1;
//		for (Result r : results) {
//			XSSFRow rowData = s.createRow(rowIdx);
//			XSSFCell cellData = rowData.createCell(0);
//			cellData.setCellValue(r.getInstanceName());
//			columnIdx = 1;
//			for (ResultInfo info : r.getResults()) {
//				cellData = rowData.createCell(columnIdx);
//				cellData.setCellValue(info.getValue());
//				columnIdx++;
//			}
//			rowIdx++;
//		}
//		try {
//			wb.write(new FileOutputStream(outputFile));
//			wb.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
}
