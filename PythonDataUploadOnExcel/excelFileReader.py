import openpyxl

def read_data(_sheet):
    return [ (row[0].value, row[1].value, row[2].value, row[3].value) for row in _sheet.rows ]

def read_data_in_file(_filePath = "d:/test.xlsx"):
    print('Excel file loading...')
    _FILE = openpyxl.load_workbook(_filePath)
    _SHEET = _FILE.active

    print('Read data in file:', _filePath)
    data = read_data(_SHEET)

    _FILE.close()
    print('File closed')

    return data