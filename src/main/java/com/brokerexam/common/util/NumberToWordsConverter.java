package com.brokerexam.common.util;


public class NumberToWordsConverter {

	public NumberToWordsConverter() {
		ConverterUtils.init(this.getClass().getSimpleName());
	}

	public String convertToWords(String number) {
		if (ConverterUtils.isEmpty(number)) {
			return "";
		}
		try {
		int integerPortion = 0;
		int decimalPortion = 0;
		int decimalPortionZeros = 0;
		int dotIndex = number.indexOf(".");
		
		if (dotIndex > 0) {
			String integerStr  = number.substring(0, dotIndex);
			
			integerPortion = Integer.valueOf(integerStr);
			String decimalStr  = number.substring(dotIndex + 1);
			
			while (decimalStr.indexOf("0") == 0) {
				decimalStr = decimalStr.substring(1);
				decimalPortionZeros++;
			}
			
			if (!ConverterUtils.isEmpty(decimalStr)) {
				decimalPortion = Integer.valueOf(decimalStr); 
			}
		} else {
			integerPortion = Integer.valueOf(number);
		}

		StringBuilder result = new StringBuilder();
		String integerResult = convert(integerPortion);
		result.append(integerResult);
		
		if (decimalPortion > 0) {
			result.append(" ").append(ConverterUtils.getWord("number_dot"));
			
			for (int i = 0;i < decimalPortionZeros; i++) {
				result.append(" ").append(ConverterUtils.getWord("units.0"));
			}
			String decimalResult = convert(decimalPortion);
			result.append(" ").append(decimalResult);
		}
		return result.toString();
		} catch (Exception e) {
			return "";
		}
	}

	private String convert(Integer i) {
		int num = i;

		if (i < 20) {
			return ConverterUtils.getWord("units." + i);
		}
		if (i < 100) {
			return ConverterUtils.getWord("tens." + i / 10)
					+ ((i % 10 > 0) ? convert(i % 10) : "");
		}
		if (i < 1000) {
			num = i / 100;

			if (num == 1) {
				return ConverterUtils.getWord("hundred")
						+ ((i % 100 > 0) ? " " + convert(i % 100) : "");
			} else {
				return ConverterUtils.getWord("units." + num) + " "
						+ ConverterUtils.getWord("hundred")
						+ ((i % 100 > 0) ? " " + convert(i % 100) : "");
			}
		}
		if (i < 1000000) {
			num = i / 1000;

			if (num == 1) {
				return ConverterUtils.getWord("thousand")
						+ ((i % 1000 > 0) ? " " + convert(i % 1000) : "");
			} else {
				return convert(i / 1000) + " "
						+ ConverterUtils.getWord("thousand")
						+ ((i % 1000 > 0) ? " " + convert(i % 1000) : "");
			}
		}
		return convert(i / 1000000) + " " + ConverterUtils.getWord("million")
				+ ((i % 1000000 > 0) ? " " + convert(i % 1000000) : "");
	}

	public static void main(String args[]) {
		NumberToWordsConverter converter = new NumberToWordsConverter();
		double d = 0;
		System.out.println(d);
		System.out.println(converter.convertToWords(d + ""));
		
		d = Utils.formatDouble(d);
        System.out.println(d); 
        System.out.println(converter.convertToWords(d + ""));
	}

}
