package define;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ExcelHelper {

    private static Workbook workbook;
    private static Sheet sheet;
    private static Iterator<Row> rowIterator;
    private static Map<String, Integer> columnIndexMap;

    // Synchronization object to control flow
    private static final Object lock = new Object();

    // Initialize the Excel reader with a given file path
    public static void init(String filePath) throws IOException {
        FileInputStream file = new FileInputStream(new File(filePath));
        workbook = new XSSFWorkbook(file); // For .xlsx files
        sheet = workbook.getSheetAt(0); // Assuming you want to read the first sheet
        rowIterator = sheet.iterator();
        columnIndexMap = new HashMap<>();

        // Read the header row and map column names to indexes
        if (rowIterator.hasNext()) {
            Row headerRow = rowIterator.next(); // The first row is the header row
            Iterator<Cell> cellIterator = headerRow.cellIterator();
            int index = 0;
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                columnIndexMap.put(cell.getStringCellValue(), index++);
            }
        }
    }

    // Check if there are more rows
    public static boolean hasNextRow() {
        System.out.println("has next row------------------------------------");
        return rowIterator != null && rowIterator.hasNext();
    }

    // Function to get the next row of data as a Map
    public static Map<String, String> getNextRowData() {
        if (rowIterator != null && rowIterator.hasNext()) {
            Row currentRow = rowIterator.next();
            Map<String, String> rowData = new HashMap<>();
            for (Map.Entry<String, Integer> entry : columnIndexMap.entrySet()) {
                String columnName = entry.getKey();
                int columnIndex = entry.getValue();

                // Get the value from the current row for the given column index
                Cell cell = currentRow.getCell(columnIndex);
                String cellValue = getCellValue(cell);

                if (cellValue != null) {
                    rowData.put(columnName, cellValue);
                } else {
                    // Optionally log or handle missing data in a way that makes sense for your tests
                    System.out.println("Missing value for column: " + columnName);
                }
            }
            return rowData;
        }
        return null; // No more rows
    }

    // Helper function to get the cell value as String
    private static String getCellValue(Cell cell) {
        if (cell == null) {
            return "N/A";  // Instead of an empty string, return null for missing values
        }
        System.out.println("Cell Type: " + cell.getCellType() + ", Raw Value: " + cell.toString());

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "N/A";  // Return null for any other unexpected cell type
        }
    }

    // This method will get the document-file pairs for the first row (index 1)
    public static List<Map<String, String>> getDocumentFilePairs() {
        List<Map<String, String>> documents = new ArrayList<>();

        Row headerRow = sheet.getRow(0); // First row with document names
        int numRows = sheet.getPhysicalNumberOfRows();

        if (headerRow != null && numRows > 1) {
            // Process only the first data row (rowIndex = 1)
            Row dataRow = sheet.getRow(1); // Read only the second row (the first row of data)
            if (dataRow != null) {
                Map<String, String> rowDocuments = new HashMap<>();
                int numCells = dataRow.getPhysicalNumberOfCells();

                for (int i = 0; i < numCells; i++) {
                    String documentName = getCellValue(headerRow.getCell(i));
                    String filePath = getCellValue(dataRow.getCell(i));

                    if (documentName != null && filePath != null && isDocumentInSwitchCase(documentName)) {
                        System.out.println("Processing document: " + documentName + " with file path: " + filePath);
                        rowDocuments.put(documentName, filePath);
                    } else {
                        System.out.println("Skipping document: " + documentName); // For debugging
                    }
                }

                if (!rowDocuments.isEmpty()) {
                    documents.add(rowDocuments);
                }
            }
        }

        return documents;  // Returns the documents after processing the first row
    }
    // Helper method to check if the document is in the switch-case
    private static boolean isDocumentInSwitchCase(String documentName) {
        // Check if document name is valid (not null or empty)
        if (documentName == null || documentName.trim().isEmpty()) {
            return false;  // Skip if the document name is empty
        }
        switch (documentName) {
            case "ID Document":
            case "Proof of Address":
            case "Proof of Income":
            case "CRS Form":
            case "IRS Form":
            case "Signature Speciman Card":
            case "ACCOUNT OPENING FORM":
            case "Key Fact Sheet":
            case "Term & Conditions":
                return true; // Return true if it matches a valid document name
            default:
                return false; // Return false for any document not in the switch-case
        }
    }

    // Process a specific row and wait for Gherkin steps to finish
    public static void processRowAndWaitForGherkin() throws InterruptedException {
        if (rowIterator != null && rowIterator.hasNext()) {
            // Process the first row (or the next row)
            Row dataRow = rowIterator.next();
            Map<String, String> rowDocuments = new HashMap<>();
            int numCells = dataRow.getPhysicalNumberOfCells();

            Row headerRow = sheet.getRow(0); // Assuming first row is header
            for (int i = 0; i < numCells; i++) {
                String documentName = getCellValue(headerRow.getCell(i));
                String filePath = getCellValue(dataRow.getCell(i));

                if (documentName != null && filePath != null && isDocumentInSwitchCase(documentName)) {
                    rowDocuments.put(documentName, filePath);
                }
            }

            if (!rowDocuments.isEmpty()) {
                System.out.println("Processing row: " + rowDocuments);
                // Simulate the task of processing the first row here
                // This is where you would trigger your Gherkin steps.
            }

            // Wait for the Gherkin steps to finish
            synchronized (lock) {
                lock.wait(); // Wait until notified to proceed
            }
        }
    }

    // Notify to proceed to the next row after Gherkin steps are done
    public static void notifyNextRow() {
        synchronized (lock) {
            lock.notify(); // Notify the waiting thread to continue processing
        }
    }

    // Process all rows stored in the excelData map
    public static void processRows() {
        while (hasNextRow()) {
            Map<String, String> rowData = getNextRowData();
            if (rowData != null) {
                System.out.println("Processing row: " + rowData);
                // Trigger your Gherkin steps or other logic here
            }
        }
    }

    // Close the workbook when done
    public static void close() throws IOException {
        if (workbook != null) {
            workbook.close();
        }
    }
}
