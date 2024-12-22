import pandas as pd
import subprocess
from utils import get_input
from mutation import read_unmutated_genes, read_complete_genes 
from phylo_tree import generate_phylo_tree 

def main():
    print("\nWelcome to PhyloGenie!")
    df = pd.DataFrame(columns=["name", "sequence"])
    while True:
        ans = get_input()
        match ans:
            case 'A':
                altered_genes = read_unmutated_genes()
                df = pd.concat([df, altered_genes], ignore_index=True)
            case 'B':
                complete_genes = read_complete_genes()
                df = pd.concat([df, complete_genes], ignore_index=True)
            case 'C':
                if df.empty:
                    print("\nNo coding sequences found. Please use options A or B to input data.")
                else:
                    print("\nData entry complete. Processing...")
                    break
            case 'D':
                confirm = input("Are you sure you want to clear previous output files? (yes/no): ").strip().lower()
                if confirm == 'yes':
                    try:
                        subprocess.run([
                            'rm',
                            '-f',
                            'sequences.fasta',
                            'aligned_sequences.fasta',
                            'tree.nwk'  
                        ], check=True)
                    except subprocess.CalledProcessError as e:
                        print(f"Error clearing files: {e}")
    generate_phylo_tree(df)
    pass

if __name__ == "__main__":
    try:
        main()
    except KeyboardInterrupt:
        print("\nExited PhyloGenie.")