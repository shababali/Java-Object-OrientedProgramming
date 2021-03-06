/*
* Name: <Shabab Ali>
* Date: <Jan 22 2018>
* Filename: <VigenereCipher.java>
* Details: <CSC115\Assn1\VigenereCipher.java>: A Java program (verified compiler - JDK8u131) 
    that meets the criteria for developing a custom VigenereCipher class which implements Cipher.
	The Cipher uses a key parameter to encrypt and decrypt Strings of lower case characters (without any spaces).
	Learning outcomes: • How to create a one-dimensional (i.e., 1D) array.
					   • How to read from and write to array elements, using both explicit and computed index values.
					   • Identify coding involving simple file input and output using streams in Java.
					   • Identify the use of exceptions in Java.
*/


//import java.util.Arrays;

public class VigenereCipher implements Cipher {
  
  
  String key;
  int[] intCipherKey;
	
	
	//constructor
	public VigenereCipher(String key) {
		this.key = key;
		this.intCipherKey = stringToIntArray(this.key);
	} 
	
	
	
	// public void	setKey(String key) - Sets the key for a simplified Vigenere cipher.
	public void	setKey(String key) {
		this.key = key;
		this.intCipherKey = stringToIntArray(this.key);
	}
	
	
	
	// public String encrypt(String plaintext) - 
	// Encrypts a string using a simplified Vigenere cipher via intCipherKey attribute.
	public String encrypt(String plaintext) {
		
		int[] intTextInput = stringToIntArray(plaintext);
		int[] encryptedIntOutput = new int[intTextInput.length];
		
		for (int i=0; i<encryptedIntOutput.length; i++) {
			//[i%this.intCipherKey.length] is necessary to account for unmatched key lengths
			encryptedIntOutput[i] = ( intTextInput[i] + (this.intCipherKey[i%this.intCipherKey.length]) ) % 26; 		
		}
		
		String result = intArrayToString(encryptedIntOutput);
		
		return result;
	
	} //end encrypt(String plaintext)
	
	
	
	// public String decrypt(String ciphertext) - 
	// Decrypts a string using a modified Vigenere cipher via intCipherKey attribute.
	public String decrypt(String ciphertext) {
		
		int[] intCipherText = stringToIntArray(ciphertext);
		int[] decryptedIntOutput = new int[intCipherText.length];
		
		for (int i=0; i<decryptedIntOutput.length; i++) {
			//[i%this.intCipherKey.length] is necessary to account for unmatched key lengths
			decryptedIntOutput[i] = ( 26 + intCipherText[i] - (this.intCipherKey[i%this.intCipherKey.length]) ) % 26;		
		}
		
		String result = intArrayToString(decryptedIntOutput);
		
		return result;
		
	} //end decrypt(String ciphertext)
	
	
	
	// private String intArrayToString(int[] encodedText) - 
	// Converts an array of integers with values in the range 0...25 
	// into a string with characters in the range a...z.
	private String intArrayToString(int[] encodedText) {
		
		String result = "";
		for (int i: encodedText) {
			char c = (char)(i + 97);
			result += c;
		}
		return result;
	
	} //end intArrayToString(int[] encodedText)
	
	
	
	// private int[] stringToIntArray(String text) - 
	// Converts a string into an int array where the values are within the range 0...25.
	private int[] stringToIntArray(String text) {
		
		int[] result = new int[text.length()];
		for (int i=0; i<text.length(); i++) {
            result[i] = (int)text.charAt(i) - 97;

        }
		return result;
	
	} //end stringToIntArray(String text)
	
	
	
	// private void dumpArray(int[] array, String text) - Prints out the specified text, 
	// followed immediately by the (comma-delimited) contents of the array.
	private void dumpArray(int[] array, String text) {
		
		System.out.print(text);
		for (int i=0; i<array.length; i++) {
			System.out.print(array[i]);
			if (i != array.length-1) {
				System.out.print(",");
			} 
		}
		
	} //end dumpArray(int[] array, String text)  
	
	
	
	// main(String[] args) used for internal testing purposes only.
	// UNDO BLOCK COMMENTS and //import java.util.Arrays TO IMPLEMENT!
	public static void main(String[] args) {

		// Test constructor
		VigenereCipher vc = new VigenereCipher("obo");
		System.out.println("\nConstruct VigenereCipher object with key:");
		System.out.println(vc.key);
		
		
		// Test setKey class method:
		vc.setKey("bob");
		System.out.println("setKey(\"bob\") Test:");
		System.out.println(vc.key + "\n");
		
		
		// Test private int[] stringToIntArray(String textdcn):
		System.out.println("Test private int[] stringToIntArray(String text):");
		String sTIA = "";
		for(char i='a'; i<='z'; i++) {
			sTIA += i;
		}
		System.out.println(sTIA);		
		System.out.println(Arrays.toString(vc.stringToIntArray(sTIA)) + "\n");
		
		
		// Test private String intArrayToString(int[] encodedText):
		System.out.println("Test private String intArrayToString(int[] encodedText):");
		int[] t = new int[26];
		for(int i=0; i<26; i++) {
			t[i] = i;
		}
		System.out.println(Arrays.toString(t));
		System.out.println(vc.intArrayToString(t) + "\n");
		
		
		// Test private void dumpArray(int[] array, String text):
		System.out.println("Test private void dumpArray(int[] array, String text):");		
		String s = "text: ";
		vc.dumpArray(t, s);
		System.out.println();
		
		
		// Test public String encrypt(String plaintext):
		System.out.println("Test VigenereCipher encrypt() and decrypt():");		
		System.out.println("Encrypt: 'themessage' with key 'bob':");
		String encryptTest = "themessage";
		System.out.println(vc.encrypt(encryptTest));
		
		
		// Test public String decrypt(String ciphertext):
		System.out.println("Decrypt:");
		String decryptTest = vc.decrypt(vc.encrypt(encryptTest));
		System.out.println(decryptTest);
	
	} //end main(String[] args)

	
} //end class VigenereCipher