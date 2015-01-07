package folio;

import folioxml.core.InvalidMarkupException;
import folioxml.directexport.DirectXmlExporter;
import folioxml.slx.SlxRecordReader;
import java.io.File;
import java.io.IOException;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

public class Main {
    
    private static final Options OPTIONS;
    
    private static final String OPTION_XML = "xml";
    private static final String OPTION_FILE = "file";
    private static final String OPTION_OUT = "out";
    
    static {
        OPTIONS = new Options();
        OPTIONS.addOption(OPTION_XML, false, "convert file from FFF to XML");
        OPTIONS.addOption(OPTION_FILE, true, "path to input FFF file");
        OPTIONS.addOption(OPTION_OUT, true, "path to output XML file");
    }
    
    public static void main(String[] args) throws ParseException, InvalidMarkupException, IOException {                        
        CommandLineParser parser = new PosixParser();
        CommandLine commandLine = parser.parse(OPTIONS, args);
        
        if (!commandLine.hasOption(OPTION_XML)) {
            System.out.println("Only converting to XML is supported.");
            return;
        }       
        
        String input = commandLine.getOptionValue(OPTION_FILE);
        if (input == null || input.trim().isEmpty()) {
            System.out.println("Path to input FFF file is not specified.");
            return;
        }
        
        String output = commandLine.getOptionValue(OPTION_OUT);
        if (output == null || output.trim().isEmpty()) {
            System.out.println("Path to output XML file is not specified.");
            return;
        }
        
        convertFromFFFtoXML(input, output);                                                
    }
    
    private static void convertFromFFFtoXML(String input, String output) throws InvalidMarkupException, IOException {
        SlxRecordReader srr = new SlxRecordReader(new File(input));
        srr.silent = false;
                
        DirectXmlExporter xh = new DirectXmlExporter(srr, output);
        
        try {
            xh.processAll();
        } finally {
            xh.close();
            srr.close();
        }
    }
}
