package helpers;

public class ReportUtils 
{
static ReportUtils reportUtils;

public static ReportUtils getReportUtils() {
	return reportUtils;
}

public static void setReportUtils(ReportUtils reportUtils) {
	ReportUtils.reportUtils = reportUtils;
}

}
