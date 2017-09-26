import java.io.*;
import java.net.*;
class iOSDeviceQuery {

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
		write ( "USAGE: java " + MainClass + "  [--build/-B] <BUILD> [--code-name/-C] <CODENAME> [--device/-D] <DEVICE>\n", 15 );
		write ( "--build/-B	The build string of the device (For example:  15A5368a, 14B72, 9B206, 15A372 etc) \n", 15 );
		write ( "--code-name/-C	The codename of the device (For example: TigrisSeed, Butler, Hoodoo, Tigris etc)\n", 15 );
		write ( "--device/-D	The device type (For Example: iPhone6,1, iPad3,4, iPhone3,3, iPad4,4 etc)\n", 15 );
		write ( "EXAMPLE: java " + MainClass + " --build 15A5368a\n", 15 );
		write ( "EXAMPLE: java " + MainClass + " --code-name Butler\n", 15 );
		write ( "EXAMPLE: java " + MainClass + " --device iPad4,4\n", 15 );
		write ( "EXAMPLE: java " + MainClass + " --device iPad4,4 -C Tigris\n\n", 15 );

		System.exit ( Return );

}

public static void main ( String[] args ) {

		if ( args.length < 2 || args.length > 6 ) {

				usage ( "Argumental error", 1 );

		}

		String Build = "";
		String CodeName = "";
		String Device = "";

		for ( int i = 0; i < args.length; i += 2 ) {

				if ( args[i].equals ( "--build" ) || args[i].equals ( "-B" ) ) {

						Build = args[i + 1];

				} else if ( args[i].equals ( "--code-name" ) || args[i].equals ( "-C" ) ) {

						CodeName = args[i + 1];

				} else if ( args[i].equals ( "--device" ) || args[i].equals ( "-D" ) ) {

						Device = args[i + 1];

				}

		}

		if ( Device == "" && Build == "" && CodeName == "" ) {

				usage ( "Argumental error", 1 );

		}

		String Line = null;
		URL URL = null;
		BufferedReader BR = null;
		InputStream IS = null;

		try {

				if ( Device != "" && Build == "" && CodeName == "" ) {

						if ( Device.contains ( "iPad" ) ) {

								URL = new URL("https://www.theiphonewiki.com/wiki/List_of_iPads");
								IS = URL.openStream();
								BR = new BufferedReader(new InputStreamReader(IS));

								while ( ( Line = BR.readLine (  ) ) != null ) {

										if ( Line.contains ( "Latest firmware" ) ) {

												Line = Line.substring ( Line.indexOf ( "\"" ) + 1 );
												Line = Line.substring ( 0, Line.indexOf ( "\"" ) );
												Line = Line.substring ( 6 );

												if ( Line.contains ( "title" ) ) {

														Line = Line.substring ( 13 );

												}

												Line = Line.substring ( 0, Line.indexOf ( ")" ) + 1 );

												Line = Line.replace ( "_", " " );

												if ( Line.contains ( Device ) ) {

														write ( "Found: " + Line + "\n", 15 );

												}

										}

								}

								Line = null;
								URL = null;
								BR = null;
								IS = null;

								URL = new URL("https://www.theiphonewiki.com/wiki/List_of_iPad_minis");
								IS = URL.openStream();
								BR = new BufferedReader(new InputStreamReader(IS));

								while ( ( Line = BR.readLine (  ) ) != null ) {

										if ( Line.contains ( "Latest firmware" ) ) {

												Line = Line.substring ( Line.indexOf ( "\"" ) + 1 );
												Line = Line.substring ( 0, Line.indexOf ( "\"" ) );
												Line = Line.substring ( 6 );

												if ( Line.contains ( "title=" ) ) {

														Line = Line.substring ( 13 );

												}

												Line = Line.substring ( 0, Line.indexOf ( ")" ) + 1 );
												Line = Line.replace ( "_", " " );

												if ( Line.contains ( Device ) ) {

														write ( "Found: " + Line + "\n", 15 );

												}

										}

								}

						} else if ( Device.contains ( "iPod" ) ) {

								URL = new URL("https://www.theiphonewiki.com/wiki/List_of_iPod_touches");
								IS = URL.openStream();
								BR = new BufferedReader(new InputStreamReader(IS));

								while ( ( Line = BR.readLine (  ) ) != null ) {

										if ( Line.contains ( "Latest firmware" ) ) {

												Line = Line.substring ( Line.indexOf ( "\"" ) + 1 );
												Line = Line.substring ( 0, Line.indexOf ( "\"" ) );
												Line = Line.substring ( 6 );

												if ( Line.contains ( "title" ) ) {

														Line = Line.substring ( 13 );

												}

												Line = Line.substring ( 0, Line.indexOf ( ")" ) + 1 );

												Line = Line.replace ( "_", " " );

												if ( Line.contains ( Device ) ) {

														write ( "Found: " + Line + "\n", 15 );

												}

										}

								}


						} else if ( Device.contains ( "iPhone" ) ) {

								URL = new URL("https://www.theiphonewiki.com/wiki/List_of_iPhones");
								IS = URL.openStream();
								BR = new BufferedReader(new InputStreamReader(IS));

								while ( ( Line = BR.readLine (  ) ) != null ) {

										if ( Line.contains ( "Latest firmware" ) ) {

												Line = Line.substring ( Line.indexOf ( "/" ) + 1 );

												Line = Line.substring ( 5 );

												if ( Line.contains ( "?title=" ) ) {

														Line = Line.substring ( Line.indexOf ( "=" ) + 1 );

												}

												String LineArray[] = Line.split ( "\"" );

												Line = LineArray[0];

												if ( Line.contains ( "&" ) ) {

														Line = Line.substring ( 0, Line.indexOf ( "&" ) );

												}

												if ( Line.contains ( "_" ) ) {

														Line = Line.replace ( "_", " " );

														if ( Line.contains ( Device ) ) {

																write ( "Found: " + Line + "\n", 15 );

														}

												}

										}

								}

						} else if ( Device.contains ( "Watch" ) ) {

								URL = new URL("https://www.theiphonewiki.com/wiki/List_of_Apple_Watches");
								IS = URL.openStream();
								BR = new BufferedReader(new InputStreamReader(IS));

								while ( ( Line = BR.readLine (  ) ) != null ) {

										if ( Line.contains ( "Latest firmware" ) ) {

												Line = Line.substring ( Line.indexOf ( "\"" ) + 1 );
												Line = Line.substring ( 0, Line.indexOf ( "\"" ) );
												Line = Line.substring ( 6 );

												if ( Line.contains ( "title" ) ) {

														Line = Line.substring ( 13 );

												}

												Line = Line.substring ( 0, Line.indexOf ( ")" ) + 1 );

												Line = Line.replace ( "_", " " );

												if ( Line.contains ( Device ) ) {

														write ( "Found: " + Line + "\n", 15 );

												}

										}

								}

						} else if ( Device.contains ( "AppleTV" ) ) {

								URL = new URL("https://www.theiphonewiki.com/wiki/List_of_Apple_TVs");
								IS = URL.openStream();
								BR = new BufferedReader(new InputStreamReader(IS));

								while ( ( Line = BR.readLine (  ) ) != null ) {

										if ( Line.contains ( "Latest firmware" ) && !Line.contains ( "3.0.2" ) ) {

												String LineArray[] = Line.split ( "\"" );
												Line = LineArray[1];

												Line = Line.substring ( 6 );

												if ( Line.contains ( "?" ) ) {

														LineArray = Line.split ( "=" );
														Line = LineArray[1];

												}

												if ( Line.contains ( "&" ) ) {

														LineArray = Line.split ( "&" );
														Line = LineArray[0];

												}

												if ( Line.contains ( "_" ) ) {

														Line = Line.replace ( "_", " " );

												}


												if ( Line.contains ( Device ) ) {

														write ( "Found: " + Line + "\n", 15 );

												}

										}

								}

						} else if ( Device.contains ( "AudioAccessory" ) ) {

								URL = new URL("https://www.theiphonewiki.com/wiki/List_of_HomePods");
								IS = URL.openStream();
								BR = new BufferedReader(new InputStreamReader(IS));

								while ( ( Line = BR.readLine (  ) ) != null ) {

										if ( Line.contains ( "Latest firmware" ) ) {

												String LineArray[] = Line.split ( "=" );
												Line = LineArray[2];

												if ( Line.contains ( "&" ) ) {

														LineArray = Line.split ( "&" );
														Line = LineArray[0];

												}

												if ( Line.contains ( "_" ) ) {

														Line = Line.replace ( "_", " " );

												}

												if ( Line.contains ( Device ) ) {

														write ( "Found: " + Line + "\n", 15 );

												}

										}

								}

						}

				} else if ( Device == "" && Build != "" && CodeName == "" ) {



				} else if ( Device == "" && Build == "" && CodeName != "" ) {



				} else if ( Device != "" && Build != "" && CodeName == "" ) {



				} else if ( Device == "" && Build != "" && CodeName != "" ) {



				} else if ( Device != "" && Build == "" && CodeName != "" ) {



				} else if ( Device != "" && Build != "" && CodeName != "" ) {



				}

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
