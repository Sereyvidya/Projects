# 🧬 OncoLens: Predicting Cancer Patient's Vital Status and Treatment Outcome

This project analyzes clinical cancer patient data and models treatment outcomes using machine learning. It aims to demonstrate how ML techniques can be applied in cancer research to uncover patterns in patient survivability and treatment response. The project also serves as a learning experience to deepen practical skills in data analysis and machine learning within a biomedical context.

## 📁 Project Structure

- `DataProcessing.ipynb`: Jupyter notebook for loading, cleaning, and preprocessing the clinical dataset (`clinical.json`). This includes handling structured patient data such as diagnoses, treatments, follow-ups, and demographics.
- `Models.ipynb`: Jupyter notebook containing machine learning pipelines for classification modeling.
- `clinical.json`: A structured dataset containing de-identified clinical records, including patient demographics, disease types, staging, treatments, outcomes, and follow-ups.

## 🚀 Usage

1. Copy the entire `OncoLens` folder into your Google Drive. 
2. Open `DataProcessing.ipynb` in Google Colab and set the `PATH` variable to project's directory.
   ```python
   # Change this to your own project path on Google Drive
   PATH = "/content/drive/MyDrive/OncoLens"
   ```
3. Run `DataProcessing.ipynb` to load and clean the data.
4. Open `Models.ipynb` in Google Colab and set the PATH variable to 
project's directory.
5. Run `Models.ipynb` to train and evaluate classification models.
6. Modify model parameters and preprocessing steps as needed to experiment with performance.

## 💡 Potential Improvements

OncoLens could be used to:
- Predict recurrence likelihood based on initial diagnosis and treatment.
- Evaluate survival trends across cancer types.
- Study treatment effectiveness (e.g., chemo vs. immunotherapy).

## 🙏 Acknowledgements

- `clinical.json` dataset was obtained by applying filters on data from the National Cancer Institute Genomic Data Commons (GDC).
- This project was completed in collaboration with **Victoria Vo** and **Jerrison Wong**. 
- I would like to thank **Professor James Casaletto** for his guidance and support throughout the development of this project.
