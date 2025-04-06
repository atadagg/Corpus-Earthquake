import os

def combine_text_files(input_folder, output_file):
    with open(output_file, 'w', encoding='utf-8') as outfile:
        for filename in sorted(os.listdir(input_folder)):  # Sort to maintain order
            file_path = os.path.join(input_folder, filename)
            if os.path.isfile(file_path) and filename.endswith('.txt'):
                with open(file_path, 'r', encoding='utf-8') as infile:
                    outfile.write('\n' + infile.read().strip() + '\n')  # Ensure a newline before and after
                print(f"Added {filename} to {output_file}")

input_folder = "src/test/java/Corpus/6-subat-2023-kahramanmaras-depremi"  # Change this to your folder path
output_file = "merged_output.txt"

combine_text_files(input_folder, output_file)
print(f"Files combined into {output_file}")
