# Naughty Bits
## Bit State Manager

**Preamble**
In the mystical digital realm of Bitlandia, there exists a curious society of bits, each carrying a unique characteristic: they are either "naughty" or "good". For aeons, these bits have lived in harmony, their states unchallenged, drifting through cyberspace in their predefined roles. However, the winds of change have begun to stir in Bitlandia, bringing with them a challenge that could alter the fabric of bit society forever.
Enter you, a renowned Bit Whisperer, known far and wide for your ability to understand and influence the deepest desires of any bit. The Elders of Bitlandia have summoned you for a task of utmost importance. Your mission, should you choose to accept it, is to create a magical tool, forged in the ancient language of Java, capable of peering into the very essence of bits, identifying their nature as naughty or good, and, if necessary, changing their state to restore balance.
Armed with your knowledge of Java and guided by the spirits of object-oriented programming, you set out to craft this tool. The Elders provide you with a scroll, an initial map of Bitlandia in the form of a binary string, where 1s are naughty bits, and 0s are good bits. Your tool must be able to interpret this map, receive commands from the inhabitants of Bitlandia, and reveal the nature of any sequence of bits upon inquiry. Furthermore, it should allow you to alter the fate of any bit, turning the naughty into good or vice versa, thus ensuring peace and balance are maintained in Bitlandia.


**Overview**
This exercise is about creating a Java program that manages a sequence of bits, identifying them as "naughty" or "good" and allowing the user to interactively check for naughty bits in a string or change the state of any bit at a specified index.


**Initial Setup**
The program initialises by reading a single line from standard input (STDIN), consisting of a binary string. In this string, 1 represents a naughty bit, and 0 represents a good bit. For instance, if the string is "00110", it means bits at positions 1 and 2 are naughty. Bits are read from right to left, where the bit on the very right is bit 0.

After this has been entered, the user can enter input in one of three formats:

> check <string>

> change <index> <state>, or

> exit

These inputs are described in more detail in the next section.


**User Interactions**
The program processes three types of commands:

> Check: Determines if a given string of bits contains any naughty bits as per the initial configuration.

> Change: Modifies the state of a bit at a specific index to either naughty or good.

> Exit: Exit the program.


**Example Usage**
- Given Initial Configuration String: "01010"
This indicates that bits at indexes 1 and 3 are initially naughty.

- Command 1: check 00100
Input: The user enters check 00100 in the command line.
Output: "No Naughty Bits Found"
Explanation: Since the only 1 in the string is at index 2, which is not marked as naughty in the initial configuration, the program outputs that no naughty bits are found.

- Command 2: change 2 naughty
Input: The user enters change 2 naughty in the command line.
Action: The program changes the bit at index 2 to naughty.
Output: "Bit at index 2 marked as Naughty"
Explanation: The state of the bit at index 2 is now naughty.

- Command 3: check 00100 (After the Change Command)
Input: The user re-enters check 00100 in the command line.
Output: "Contains Naughty Bits"
Explanation: Since the bit at index 2 was marked as naughty by the previous command, and the check string has a 1 at index 2, the program now identifies this as containing naughty bits.


**Running the Program**
1. Starting the Program:
When the program starts, it asks for a single line of input with the prompt Initial bit configuration:  
The program then waits for user input in the form of commands.

- Entering Commands:
The user enters commands directly into the command line.
Based on the command type (check or change), the program performs the corresponding action and provides an output.

2. Feedback and Interaction:
After processing a command, the program outputs the result.
The user can continue to input commands, interacting with the program in real time.

3. Deliverables
BitManager.java: Contains the logic for managing and interacting with the bit sequence. Upon executing this program the application should run.


**Example Runs of the Program**

`Welcome to Bitlandia!
Initial bit configuration: 01010

Please enter a command (check <string>, change <index> <state>, or exit):

> check 00100
No Naughty Bits Found

> change 2 naughty
Bit at index 2 marked as Naughty

> check 00100
Contains Naughty Bits

> exit
Exiting program. Goodbye!`



**Errors**
Your program will need to handle the following errors by printing the corresponding output, followed by a newline. No other errors will be checked.
