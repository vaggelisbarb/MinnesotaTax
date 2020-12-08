package inputManagePackage;

public class Parser extends FileDataHandler {

	@Override
	public String getParameterValueFromFileLine(String fileLine,
			String parameterName, String extension) {
		String parameterValue = null;
		if (extension == "txt")
			// +2 is for : and space 
			parameterValue = fileLine.substring(parameterName.length(),
												fileLine.length()+2);
		else if (extension == "xml") {
			String parameterStartField = "<"+parameterName+"> ";
			String parameterEndField = " </"+parameterName+">"; 
			parameterValue = fileLine.substring(parameterStartField.length(),
												fileLine.length() - 
												parameterEndField.length());
		}
		
		return parameterValue;
	}

	@Override
	public boolean receiptsCheckingConditions(String extension,
			String fileLine) {
		if (fileLine.equals("")) {
			if (extension == "txt") {
				if (fileLine.indexOf("Receipts:") != -1)
					return true;
				else
					return false;
			}else if (extension == "xml") {
				if (fileLine.indexOf("<Receipts>") != -1) {
					if (fileLine.indexOf("</Receipts>") == -1)
						return true;
					else return false;
				}else
				return false;
			}
			System.out.println("Cannot check receipt condition for ."+extension+" file");
			return false;
		}else
			return false;
	}

	
}
