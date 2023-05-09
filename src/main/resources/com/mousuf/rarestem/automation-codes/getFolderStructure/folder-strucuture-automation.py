import os

def create_tree_structure(path, output_file, level=0):
    with open(output_file, 'a') as f:
        f.write('|  ' * level + '|--' + os.path.basename(path) + '\n')
    if os.path.isdir(path):
        for file in os.listdir(path):
            create_tree_structure(os.path.join(path, file), output_file, level + 1)

# Change path for folder to get strucutre from
folder_path = 'C:/Users/mousu/Desktop/mvc-edit-cgpt'
# Chage path to desired text file output
output_file_path = 'C:/Users/mousu/Desktop/final-project-line-count/datastr.txt'
if not os.path.exists(output_file_path):
    os.mkdir(output_file_path)
create_tree_structure(folder_path, output_file_path)
