import requests, json
import upload.excelFileReader as EFR
import time

EXCEL_FILE_PATH = 'FILEPATH in here'
HOST = 'HOST in here'
HEADER = {'Content-Type': 'application/json; charset=utf-8'}

def request_to_server_roadspot( _data):
    errors = []
    BASE_PATH = '/api/roadspot'

    for row in _data:
        time.sleep(0.5)
        _spotNumber, _latitude, _longitude, _connected, _isOnRoad = row
        if _isOnRoad == 'T' : _isOnRoad = True
        else : _isOnRoad = False


        uri = HOST + BASE_PATH + '/' + str(_spotNumber)

        try:
            splitted = _connected.split('/')
            _connected = []
            for number in splitted: _connected.append(int(number))
        except:
            try:
                _connected = [int(_connected)]
            except:
                _connected = []

        data = {
            'gps' : {
                'latitude' : _latitude,
                'longitude' : _longitude
            },
            'connected' : _connected,
            'isOnRoad' : _isOnRoad
        }
        print('URI:', uri)
        res = requests.post(url = uri,
                            headers=HEADER,
                            data = json.dumps(data))

        print('Request data: {_data} -Response Code:{_resCode}'.format(_data=data,
                                                                       _resCode=res.status_code))
        if res.status_code >= 300 :
            errors.append( (data, res.status_code))
    print('Request all data to server')

    if len(errors) > 0 :
        print('Errors[{}]:', len(errors))
        for error in errors:
            print( error)

def request_to_server_building( _data):
    errors = []
    BASE_PATH = '/api/building'

    for row in _data:
        time.sleep(0.5)
        _buildingNumber, _name, _latitude, _longitude = row

        uri = HOST + BASE_PATH + '/' + str(_buildingNumber)

        data = {
            'name': _name,
            'gps': {
                'latitude': _latitude,
                'longitude': _longitude
            },
        }
        print('URI:', uri)
        res = requests.post(url=uri,
                            headers=HEADER,
                            data=json.dumps(data))

        print('Request data: {_data} -Response Code:{_resCode}'.format(_data=data,
                                                                       _resCode=res.status_code))
        if res.status_code >= 300:
            errors.append((data, res.status_code))
    print('Request all data to server')

    if len(errors) > 0:
        print('Errors[{}]:', len(errors))
        for error in errors:
            print(error)

if __name__ == '__main__':
    data = EFR.read_data_in_file( _filePath= EXCEL_FILE_PATH)
    # request_to_server_roadspot( data)
    request_to_server_building( data)
    print('Done')