import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ScrabbleSolver {

	public static void main(String[] args) throws IOException {

		Scanner scanner = new Scanner(System.in);

		System.out.println("Please line stream of letters");

		String letters = scanner.nextLine().toUpperCase();

		Map<Character, Integer> lettersCountMap = getCharacterCountMap(letters);

		// System.out.println(lettersCountMap);

		System.out.println("List of Possible Words:");

		FileReader file = new FileReader("/Users/rahuldusaje/Downloads/dictionary.txt");
		BufferedReader reader = new BufferedReader(file);

		for (String currentWord = reader.readLine(); currentWord != null; currentWord = reader.readLine()) {

			Map<Character, Integer> currentWordCountMap = getCharacterCountMap(currentWord);

			boolean canMakeWord = true;

			for (Character character : currentWordCountMap.keySet()) {

				int countCharactersRequiredInCurrentWord = currentWordCountMap.get(character);

				int countCharacetrsInGivenInputLetter = 0;

				if (lettersCountMap.containsKey(character)) {
					countCharacetrsInGivenInputLetter = lettersCountMap.get(character);

				} else
					countCharacetrsInGivenInputLetter = 0;

				if (countCharactersRequiredInCurrentWord > countCharacetrsInGivenInputLetter) {

					canMakeWord = false;
					break;
				}

			}

			if (canMakeWord) {
				System.out.println(currentWord);
			}

		}

		scanner.close();
		reader.close();
	}

	private static Map<Character, Integer> getCharacterCountMap(String letters) {
		Map<Character, Integer> characterCountMap = new HashMap<>();

		for (char i : letters.toCharArray()) {
			if (characterCountMap.containsKey(i)) {
				characterCountMap.put(i, (characterCountMap.get(i) + 1));
			} else
				characterCountMap.put(i, 1);
		}

		return characterCountMap;
	}

}
