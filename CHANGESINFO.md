## Sentence Splitter Modification

**File:** `src/main/java/Corpus/SentenceSplitter.java`
**Method:** `split(String line)`
**Change:** Removed the requirement for the character following sentence-ending punctuation (`.`, `?`, `!`, `…`) to be uppercase or a digit for a sentence split to occur.
**Reason:** The input data frequently contains sentences starting with lowercase letters after punctuation, causing the original splitter logic to fail. (eksisozluk.com)
**Modification Details:** The `isNextCharUpperCaseOrDigit` checks were removed from the conditional logic that adds a completed `Sentence` object to the results list (around lines 364, 370, 376 in the original file structure). This allows splits based primarily on punctuation and context (parentheses, quotes) regardless of the subsequent character's case.
**Potential Side Effect:** May cause over-splitting if abbreviations ending in periods are followed by lowercase words (e.g., "vs. the others"). Further testing is advised.


ai is too weird

I've added some extra abbreviation patterns to ignore in the TurkishSplitter.java


Changed the webmode logic to work properly under the new rules.