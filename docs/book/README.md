## Глава 11. Аппаратные средства смартфона/планшета     219                                               

requestWindowFeature(Window.FEATURE_NO_TITLE);
getWindow().setFlags(WindowМanager.LayoutPararns.FLAG_FULLSCREEN,
WindowМanager.LayoutPararns.FLAG_FULLSCREEN);
setContentView(R.layout.main);
//Поиски установка SurfaceHolder
Preview = (SurfaceView)findViewByid(R.id.surface);
PreviewHolder = Preview.getHolder();
PreviewHolder.addCallback(this);
PreviewHolder.setType(SurfaceHolder.SURFACE_TYPE PUSH BUFFERS);
minflater = Layoutinflater.from(this);
View overView = minflater.inflate(R.layout.cameraoverlay, null);
this.addContentView(overView,
new LayoutPararns(LayoutPararns.FILL_PARENT,
LayoutParams.FILL_PARENT));

// Поиск кнопки
GetPicture = (Button) findViewByid(R.id.button);
// Устанавливаем обработчик нажатия кнопки
GetPicture.setOnClickListener(new OnClickListener() {
puЬlic void onClick(View view) {
// Вызываем метод takePicture() для получения картинки
Caml.takePicture(Shutter, PicCallback, compress);

// Пустая заглушка - ничего не делаем
ShutterCallback Shutter = new ShutterCallback() {
@Override,
puЬlic void onShutter() {}
} ;

// Пустая заглушка - ничего не делаем
PictureCallback PicCallback = new PictureCallback()
puЬlic void onPictureTaken(byte[] data, Camera с) {}
} ;
// Заполняем массив с временными данными и вызываем
/ / функцию done ()
PictureCallback compress = new PictureCallback() {
puЬlic void onPictureTaken(byte[] data, Camera с) {
if(data !=null) {
tempdata=data;
done ();
}
}

## Часть 11. Построение сложного приложения    220
void done() {
// Получаем растровое изображение путем декодирования
// массива tempdata
Bitmap Ьm = BitmapFactory.decodeByteArray(tempdata,
О, tempdata.length);
String url = Images.Media.insertimage(getContentResolver(),
Ьm, null, null ) ;
Ьm. recycle () ;
Bundle bundle = new Bundle();
if(url!=null) {
bundle.putString("url", url);
Intent mlntent = new Intent();
mintent.putExtras(bundle);
setResult(RESULT_OK, mlntent);
} else {
Toast.makeText(this, "О!ш,~бка!",
Toast.IENGTH_SHORT) .show();
finish ();

// Реакция на изменение поверхности
@Override
puЫic void surfaceChanged(SurfaceHolder holder, int format,
int w, int h) {
try 1
if (mPreviewRunning) {
Caml.stopPreview();
// Останавливаем предварительный просмотр
mPreviewRunning = false;
// Получаем параметры камеры
Camera.Parameters р = Caml.getParameters();
// Устанавливаем размер предварительного просмотра
p.setPreviewSize(w, h);
// Устанавливаем параметры камеры
Caml.setParameters(p);
// Устанавливаем владельца поверхности
Caml.setPreviewDisplay(holder);
// Запускаем предварительный просмотр
Caml.startPreview();
// Флаг предварительного просмотра
mPreviewRunning = true;
catch(Exception е) {
// Действие в случае исключения, эдесь нужно вывести или
// сообщение об ОЦD,1бке или проанализировать ошибку. Для простоты
// этого примера NЫ ничего не будем делать 
}
}

## Глава 11. Аппаратные средства смартфона/планшета      221
@Override
puЬlic void surfaceCreated(SurfaceHolder holder) {
// Действие при создании поверхности - открываем камеру
Carnl = Carnera.open();
@Override
puЬlic void surfaceDestroyed(SurfaceHolder holder)
// Останавливаем предварительный просмотр
Carnl.stopPreview();
// Сбрасываем флаг
mPreviewRunning = false;
// Освобождаем ресурсы
Carnl.release();
Carnl=null;

# 11.3. Работаем с Bluetooth 
Bluetooth - это очень популярная беспроводная технология, которую можно
использовать как для подкmочения дополнительных устройств (например, телефонной гарнитуры hands free ), так и для передачи файлов. Подробного экскурса
в исторшо и технические характеристики Bluetooth в этой книге не будет, благо
в Интернете этой информации предостаточно. Мы же поговорим о программировании Bluetooth в Android.
Для осуществления передачи данных по Bluetooth нужно включить адаmер
Bluetooth, найти доступные устройства (другие смартфоны, ноутбуки, планшеты
или стационарные компьютеры с включенным Bluetooth-aдanтepoм), подключиться
к одному из этих устройств и произвести обмен данными.
Чтобы использовать Bluetooth-aдanтep, нужно в файл манифеста добавить следующие строки:
<uses-permission android:narne="android.permission.BLUETOOТH" />
<uses-permission android:narne="android.permission.BLUETOOTH_ADMIN" />
В пакете android.Ыuetooth определены следующие классы:
□ BluetoothAdapter - представляет интерфейс обнаружения и установки Вluеtооth-соединений;
□ BluetoothClass - описывает общие характеристики Bluetooth-ycтpoйcтвa;
□ BluetoothDevice - представляет удаленное Bluetooth-ycтpoйcтвo;
□ BluetoothSocket - сокет или точка соединения для данных, которыми наша система обменивается с другим Вluеtооth-устройством;
□ BluetoothServersocket - сокет для прослушивания входящих Вluеtооth-соединений

## Часть 111. Построение сложного приложения 222
Итак, приступим к реализации первого этапа обмена данными по Bluetooth, а именно - к включению Bluetooth-aдaптepa. Прежде всего нужно получить адаптер по
умолчанию:
BluetoothAdapter Bluetoothl = BluetoothAdapter.getDefaultAdapter();
Активировать Bluetooth-aдaптep можно с помощью следующего кода:
// Если Bluetooth-aдaптep выключен
if(!Bluetoothl.isEnaЬled()) {
// Создаем действие ACTION_REQUEST_ENAВLE - запрашивает включение
// адаптера
Intent eintent = new Intent(BluetoothAdapter.ACТION_REQUEST_ENAВLE);
// ВЬmолняем действие
startActivity(eintent);
}
Затем нужно обнаружить Bluetooth-ycтpoйcтвa, расположенные по соседству. Для
упрощения кода найденные устройства мы будем записывать в журнал с помощью
параметра Log (листинг 11.4). Журнал можно будет просмотреть в Android Studio
при запуске приложения. В реальном приложении вы должны будете отобразить
найденные устройства в виде списка и предоставить пользователю выбор устройства.
Лмеtмнr 11.4. Лоиск Bluetooф:.ycтpoio'nl
import android.util.Log;
private final BroadcastReceiver myReceiver = new BroadcastReceiver()
puЫic void onReceive(Context context, Intent intent) {
String action = intent.getAction();
// Когда найдено устройство
if (BluetoothDevice.ACTION_FOUND.equals(action));

// Получаем объект BluetoothDevice из Intent
BluetoothDevice device = intent.getParcelaЬleExtra(
BluetoothDevice.EXTRA_DEVICE);
// ВЫводим сообщение в журнал
Log.v("BlueTooth found: ",device.getName() + "\n"
+ device.getAddress());
IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
registerReceiver(myReceiver, filter);
Bluetoothl.startDiscovery();

Затем нужно подключиться к устройству, которое выбрал пользователь. Надо отметить, что вы можете разработать как серверное, так и клиентское приложение. Сер-

## Глава 11. Аппараmныв средства смарmфонв/планшвта 223
верное приложение будет ожидать, что к нему подключится клиент, а клиентское
станет подключаться к серверу. Код серверного приложения приведен в листинге 11.5. 

// Класс AcceptBluetoothThread принимает входящие запросы
private class AcceptBluetoothThread extends Thread {
}
private final BluetoothServerSocket myServerSocket;
puЬlic AcceptThread() {
// Используем временный объект
BluetoothServerSocket trnp = null;
try {
// МY_UUID - идентификатор, также используемый клиентом
trnp = mAdapter.listenUsingRfcorrmWithServiceRecord(NAМE,МY_UUID);
} catch (IOException е) { }
// Присваиваем trnp члену класса myServerSocket
myServerSocket = trnp;
}
puЬlic void run() {
BluetoothSocket socket = null;
// Прослушиваем соеш,,хнения
while (true) {
try { // Принимаем соеш,,хнение
socket = myServerSocket.accept();
} catch (IOException е) {
break;
// Если соединение было принято
if (socket != null) {
// Производим обработку соеш,,хнения - в отдельном потоке
DoSomethingWith(socket);
// После обработки соеш,,хнения закрываем сокет
myServerSocket.close();
break;
// Действие в случае отмены соеш,,хнения
puЬlic void cancel() {
try { // Закрываем сокет
myServerSocket.close();
}
}
}

## Часть 111. Построение сложного приложения    224
Код клиентского приложения приведен в листинге 11.6.
private class ConnectThread extends Thread
private final BluetoothSocket rnySocket;
private final BluetoothDevice rnyDevice;
puЫic ConnectThread(BluetoothDevice device)
// Используем временный объект
BluetoothSocket trnp = null;
rnyDevice = device;
// Получаем BluetoothSocket для соединения с BluetoothDevice
try {
// МY_UUID - идентификатор, такой же использует сервер
trnp = device.createRfcommSocketToServiceRecord{МY_UUID);
catch (IOException е) { }
rnySocket = trnp;
}
puЬlic void run(} {
// Отключаем обнаружение устройств, поскольку оно замедляет
// соединение
rnAdapter.cancelDiscovery();
try {
// Соединяемся с устройством через сокет
rnySocket.connect();
catch (IOException connectException) {
// Невозможно подключиться, закрываем сокет
try {
rnySocket.close();
} catch (IOException closeException) { }
return;
// Соединение установлено, производим его обработку в
// отдельном потоке. Далее можно сделать все, что угодно, например,
// передать данные в Bluetooth-coкeт, как в обычный сокет
DoSornethingWith(rnySocket);
// Отмена соединения, закрываем сокет
puЫic void cancel() {
try {
rnySocket.close();
} catch (IOException е) { 

## Глава 11. Аппаратные средства смартфона/планшета    225
# 11.4. Виброзвонок
Для привлечения внимания к уведомлению или диалоговому окну программы
можно использовать вибрацию. Для управления виброзвонком надо добавить
в файл манифеста следующее разрешение:
<uses-permission android:narne="android.permission.VIBRATE" />
Затем следует задействовать класс Vibrator:
Vibrator Vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
Vib.vibrate(2000); // Вибрировать 2 секунды
Для принудительной отмены вибрации (например, вы установили вибрацию две
секунды, а пользователь отреагировал раньше) используется метод cancel ():
Vib.cancel();
# 11.5. Набор номера
Прежде чем мы рассмотрим более сложный пример с определением номера входящего звонка, давайте разберемся, как выполнить набор номера.
Прежде всего в файл манифеста нужно добавить разрешение на исходящий звонок:
<uses-permission android:narne="android.permission.CALL_PHONE" />
Далее вы можете использовать одну из двух операций: или ACTION _ CALL, или
ACTION _ DIAL. Первая операция отобразит диалоговое окно с набираемым номером
(как обычно бывает при наборе номера вручную), вторая операция наберет номер
без показа какого-либо интерфейса пользователя.
Вот пример использования этих двух операций:
startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:11122233344")));
startActivity(new Intent(Intent.ACТION_DIAL, Uri.parse("tel: 11122233344")));
11.6. Определение номера входящего звонка
Иногда нужно выполнить какие-либо действия только при определенном состоянии
смартфона - например, при входящем звонке или при завершении звонка. В этом
случае вам требуется прослушивать состояние устройства - как только будет обнаружен входящий (исходящий) звонок, вам надо будет произвести какие-нибудь
действия, - например, начать запись разговора.
Для прослушивания состояния смартфона с целью ожидания какого-то события
используются так называемые прослушки.
В этой книге мы не станем рассматривать все имеющиеся прослушки, а разберемся
только с самой часто используемой - PhoneStateListener. LISTEN _ CALL _ STATE, позволяющей определить номер входящего звонка (и, соответственно, выполнить определенные действия при входящем звонке). Возможны три состояния звонка:
