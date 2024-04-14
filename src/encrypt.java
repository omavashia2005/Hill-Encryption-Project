import java.util.Scanner;

public class encrypt
{
    public static void main(String[] args)
    {
        //scanner declaration
        Scanner sc = new Scanner(System.in);

        //asking for user input
        System.out.println("Enter a message to encrypt: ");
        String message = sc.nextLine();

        // key matrix
        int [][] key = {{1,0,0},{0,1,0},{0,0,1}};


        //encrypts message
        System.out.println("Encrypting...");
        String encryptedMessage = hillEncrypt(message);

        //display encrypted message

        System.out.println(encryptedMessage);

    }

    // return final encrypted string
    public static String hillEncrypt(String input)
    {
        // character to add in case the stirng without spaces is not a perfect multiple of 3
        final String nullChar = "x"; // option-shift-\

        //remove spaces
        input = removeSpaces(input);
        int requiredLength = input.length();

        //checking if requiredLength is a multiple of three.
        // if not, add nullChar till it becomes a multiple of 3
        if(input.length()%3!=0)
        {
            int counter = 0;
            do
            {
                requiredLength++;
            }while (requiredLength%3!=0);

            int diff = requiredLength - input.length();
            do
            {
                input = input.concat(nullChar);
                counter++;
            }while (counter < diff);
        }


        String encrypted = "";

        //adding 3 characters at a time
        for(int i=0;i<requiredLength;i=i+3)
        {
            encrypted = encrypted.concat(matrixEncrypt((input.substring(i,i+3))));
        }

        return encrypted;
    }

    // return encrypted substring
    public static String matrixEncrypt(String a)
    {

        //Declaring key matrix
        final int [][] Key = new int[3][3];

        //Initializing key matrix
        Key[0][0] = 1;
        Key[0][1] = -2;
        Key[0][2] = 2;
        Key[1][0] = 2;
        Key[1][1] = 1;
        Key[1][2] = 1;
        Key[2][0] = 3;
        Key[2][1] = 4;
        Key[2][2] = 5;

        //Storing ASCII Values

        int [] charValues = new int[3];
        charValues[0] = (int)a.charAt(0);
        charValues[1] = (int)a.charAt(1);
        charValues[2] = (int)a.charAt(2);


        int [] encryptedString = new int[3];

        //Encrypting (multiplies with key matrix)

        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                encryptedString[i] += charValues[j] * Key[j][i];
            }
        }

        //Converting to alphabet
        for(int i=0;i<3;i++)
        {
            encryptedString[i] %=26;
        }

        for(int i=0;i<3;i++)
        {
            encryptedString[i] +=97;
        }

        String encrypted="";
        for(int i=0;i<3;i++)
        {
            encrypted = encrypted.concat(Character.toString((char)encryptedString[i]));
        }

        return encrypted;
    }

    //removes spaces
    public static String removeSpaces(String message)
    {
        // trims the string
        message=message.trim();

        //new string with no spaces
        String modifiedMessage="";

        //removes spaces
        for(int i =0;i<message.length();i++)
        {
            if(Character.isWhitespace(message.charAt(i)))
            {
                modifiedMessage = modifiedMessage.concat(Character.toString(message.charAt(i+1)));
                i++;
            }
            else
                modifiedMessage = modifiedMessage.concat(Character.toString(message.charAt(i)));
        }
        return modifiedMessage;
    }
}
