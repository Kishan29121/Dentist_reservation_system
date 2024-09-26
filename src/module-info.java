/**
 * 
 */
/**
 * 
 */
module KishanPanchalHW1 {
	requires java.desktop;
	requires jdk.httpserver;
	requires jcalendar;
	requires com.google.gson;
	requires com.fasterxml.jackson.core;
	requires com.fasterxml.jackson.databind;
	opens models to com.fasterxml.jackson.databind;
	requires java.net.http;
}