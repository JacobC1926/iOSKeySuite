import java.io.*;
import java.net.*;
class iOSKeyQuery {

public static void write ( String Message, int Time ) {

	char[] MessageChar = Message.toCharArray ( );

	for ( int i = 0; i < MessageChar.length; i++ ) {

		System.out.print( MessageChar[i] );

		try {

			Thread.sleep( Time );

		} catch ( Exception e ) {

			break;

		}

	}

}

public static void usage ( String Error, int Return ) {

	StackTraceElement[] Stack = Thread.currentThread (  ).getStackTrace (  );
	StackTraceElement Main = Stack[Stack.length - 1];
	String MainClass = Main.getClassName ();

	write ( "\nERROR: " + Error + "\n\n", 15 );
	write ( "USAGE: java " + MainClass + " [--device/-D] <DEVICE> [--build/-B] <BUILD>\n", 15 );
	write ( "--build/-B		The build string of the device (For example:  15A5368a, 14B72, 9B206, 15A372 etc) \n", 15 );
	write ( "--device/-D		The device type (For Example: iPhone6,1, iPad3,4, iPhone3,3, iPad4,4 etc)\n\n", 15 );
	write ( "EXAMPLE: java " + MainClass + " -D iPhone6,1 -B 15A5368a\n", 15 );
	write ( "EXAMPLE: java " + MainClass + " --device iPad3,4 --build 14B72\n", 15 );
	write ( "EXAMPLE: java " + MainClass + " -D iPhone3,3 --build 9B206\n", 15 );
	write ( "EXAMPLE: java " + MainClass + " --device iPad4,4 -B 15A372\n\n", 15 );

	System.exit ( Return );

}

public static void main ( String[] args ) {

	if ( args.length != 4 ) {

		usage ( "Not enough arguments", 1 );

	}

	String Device = "";
	String Build = "";

	for ( int i = 0; i < args.length; i += 2 ) {

		if ( args[i].equals ( "--device" ) || args[i].equals ( "-D" ) ) {

			Device = args[i + 1];

		} else if ( args[i].equals ( "--build" ) || args[i].equals ( "-B" ) ) {

			Build = args[i + 1];

		}

	}

	if ( Device == "" || Build == "" ) {

		usage ( "Not enough arguments", 1 );

	}

	String DeviceReplaced = Device.replace ( ",", "%2C" );

	String Line = null;
	InputStream IS = null;
	int i = 0;

	try {

		write ( "Sending query to server (1/2)", 15 );

		URL URL = new URL("https://www.theiphonewiki.com/w/index.php?title=Special%3ASearch&profile=default&search=" + DeviceReplaced + "+" + Build + "&fulltext=Search");
		IS = URL.openStream();
		BufferedReader BR = new BufferedReader(new InputStreamReader(IS));
		String InnerURL = null;

		while ( ( Line = BR.readLine (  ) ) != null && i == 0 ) {

			if ( Line.contains ( Build ) && Line.contains ( "mw-search-result-heading" ) ) {

				Line = Line.substring ( Line.indexOf ( "\"" ) + 1 );
				Line = Line.substring ( 0, Line.indexOf ( "\"" ) );
				Line = Line.substring ( 6 );
				String LineArray[] = Line.split ( "_" );
				Line = LineArray[0];

				InnerURL = Line;

				i++;

			}

		}

		System.out.print ( "\b\b\b\b2/2)" );

		URL = new URL("https://www.theiphonewiki.com/w/index.php?title=" + InnerURL + "_" + Build + "_(" + Device + ")&action=edit");
		IS = URL.openStream();
		BR = new BufferedReader(new InputStreamReader(IS));
		InnerURL = null;
		write ( "\n", 15 );

		System.out.println ( "----------------------------------------------------------------------------------------------" );

		while ( ( Line = BR.readLine (  ) ) != null) {

			if ( Line.contains ( "|" ) && !Line.contains ( "<script>" ) ) {

				Line = Line.substring ( 3 );

				System.out.println ( Line );

			}

		}

		System.out.println ( "----------------------------------------------------------------------------------------------" );

	} catch ( MalformedURLException MURLE ) {

		usage ( "Malformed URL exception", 1 );

	} catch (IOException IOE) {

		usage ( "I/O exception", 1 );

	} finally {

		try {

			if ( IS != null ) {

				IS.close (  );

			}

		} catch ( IOException IOE ) {

			// nothing to see here

		}

	}

}

}
