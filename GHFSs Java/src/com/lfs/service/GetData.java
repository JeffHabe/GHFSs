package com.lfs.service ;

import java.io.BufferedReader ;
import java.text.DecimalFormat ;
import java.util.ArrayList ;
import java.util.regex.Matcher ;
import java.util.regex.Pattern ;

public class GetData
{

	public static DecimalFormat dfT = new DecimalFormat( "#.#" ) ;
	public static DecimalFormat dfH = new DecimalFormat( "##" ) ;
	StringBuffer strData = null ;

	public GetData ()
	{
	}

	public ArrayList < String > ADdata ( BufferedReader br , String port , double picByte )
	{
		strData = new outputService().outputString( br , port ) ;
		String str = strData.toString() ;
		ArrayList < String > strValues = new ArrayList() ;

		String patternString1 = "[1-9][0-9][0-9][0-9]|[1-9][0-9][0-9]|[1-9][0-9]|((?<=,)[0-9])|((?<==)[0-9])|((?<=>)[0-9])" ;
		Pattern pattern = Pattern.compile( patternString1 ) ;
		Matcher matcher = pattern.matcher( str ) ;

		while (matcher.find())
		{

			strValues.add( matcher.group( 0 ) ) ;
		}

		return strValues ;
	}

	public static String value2Volt ( String strValue , double picByte )
	{
		double value = Double.parseDouble( strValue ) ;
		DecimalFormat df = new DecimalFormat( "###.#" ) ;

		value = value * 5.0D / picByte ;
		return df.format( value ) ;
	}

	public static String Value2Temperature ( String strValue , double picByte )
	{
		double value = Double.parseDouble( strValue ) ;

		value = value * 500.0D / picByte ;
		return dfT.format( value ) ;
	}

	public static String Value2TemperatureK2C ( String strValue , double picByte )
	{
		double value = Double.parseDouble( strValue ) ;

		value = value * 500.0D / picByte - 273.15D ;
		return dfT.format( value ) ;
	}

	public static String Value2Humidity ( String strValue , double picByte , double t )
	{
		double Vm = Double.parseDouble( value2Volt( strValue , picByte ) ) ;

		if (t <= 10.0D)
		{
			Vm -= 0.3D ;
		}

		double humidity = 31.847D * Vm - 25.0955D ;
		if (humidity >= 100.0D)
		{
			humidity = 100.0D ;
		}

		String strhumidity = dfH.format( humidity ) ;
		return strhumidity ;
	}
}
