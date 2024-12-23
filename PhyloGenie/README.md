# PhyloGenie

PhyloGenie is an application that processes gene sequence files and computes a phylogenetic tree.

## Prerequisites
To run PhyloGenie, you will need the following:

- Python 3.12
- NumPy
- Pandas
- Matplotlib
- ClustalOmega
- BioPython

You can install the necessary dependencies using Homebrew:

```bash
brew install biopython
brew install clustal-omega
```

## Example Input Files
humans_tp53.csv: Contains a wildtype gene sequence followed by mutations (for option A).
animals_tp53.csv: Contains only gene sequences (for option B).

## Running PhyloGenie
After ensuring the prerequisites are installed, you can run the application using the following command:

```bash
python main.py
```

The program will compute the phylogenetic tree based on the provided gene sequence files.
