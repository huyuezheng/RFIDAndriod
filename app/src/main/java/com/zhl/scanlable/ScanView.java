package com.zhl.scanlable;

import com.uhf.scanlable.UHFLib;
import com.uhf.scanlable.UHfData.UHfGetData;

import android.app.Activity;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ScanView extends Activity implements OnClickListener {

	private TextView tvVersion;
	private Spinner tvpowerdBm;
	private Spinner tvBeep;

	private Button bSetting;
	private Button bRead;

	private Handler mHandler;

	UHFLib uhf = null;

	private SoundPool soundpool = null;
	private int soundid;
	private int tty_speed = 57600;
	private byte addr = (byte) 0xff;
	private String[] strBand = new String[5];
	private String[] strmaxFrm = null;
	private String[] strminFrm = null;
	Spinner spBand;
	Spinner spmaxFrm;
	Spinner spminFrm;

	private ArrayAdapter<String> spada_Band;
	private ArrayAdapter<String> spada_maxFrm;
	private ArrayAdapter<String> spada_minFrm;
	private static final String TAG = "SacnView";

	private static final int MSG_SHOW_PROPERTIES = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub properties
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scan_view);
		initView();

		mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				switch (msg.what) {
				case MSG_SHOW_PROPERTIES:
					String Version = String
							.valueOf(UHfGetData.getUhfVersion()[0])
							+ "."
							+ String.valueOf(UHfGetData.getUhfVersion()[1]);
					showResult(Version, UHfGetData.getUhfBand(),
							UHfGetData.getUhfMinFre()[0],
							UHfGetData.getUhfMaxFre()[0],
							UHfGetData.getUhfdBm()[0],
							UHfGetData.getUhfTime()[0],
							UHfGetData.getUhfBeepEn()[0],
							UHfGetData.getUhfAnt()[0]);
					break;
				default:
					break;
				}
			}
		};
 
	}
	
	@Override
	protected void onResume() {
		onClick(bRead);
		super.onResume();
	}

	private void initView() {

		tvVersion = (TextView) findViewById(R.id.version);

		tvpowerdBm = (Spinner) findViewById(R.id.power_spinner);
		ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(
				this, R.array.Power_select,
				android.R.layout.simple_spinner_item);
		adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		tvpowerdBm.setAdapter(adapter3);
		tvpowerdBm.setSelection(30, true);

		tvBeep = (Spinner) findViewById(R.id.beep_spinner);
		ArrayAdapter<CharSequence> adapter4 = ArrayAdapter
				.createFromResource(this, R.array.beep_select,
						android.R.layout.simple_spinner_item);
		adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		tvBeep.setAdapter(adapter4);
		tvBeep.setSelection(0, true);

		bSetting = (Button) findViewById(R.id.pro_setting);
		bRead = (Button) findViewById(R.id.pro_read);

		bSetting.setOnClickListener(this);
		bRead.setOnClickListener(this);
		strBand[0] = "Chinese band2";
		strBand[1] = "US band";
		strBand[2] = "Korean band";
		strBand[3] = "EU band";
		strBand[4] = "Chinese band1";

		spBand = (Spinner) findViewById(R.id.band_spinner);
		spada_Band = new ArrayAdapter<String>(ScanView.this,
				android.R.layout.simple_spinner_item, strBand);
		spada_Band
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spBand.setAdapter(spada_Band);
		spBand.setSelection(1);
		SetFre(2);
		spBand.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				arg0.setVisibility(View.VISIBLE);
				if (arg2 == 0)
					SetFre(1);
				if (arg2 == 1)
					SetFre(2);
				if (arg2 == 2)
					SetFre(3);
				if (arg2 == 3)
					SetFre(4);
				if (arg2 == 4)
					SetFre(8);
			}

			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
	}

	@Override
	public void onClick(View view) {
		if (view == bSetting) {
			int MaxFre = 0;
			int MinFre = 0;
			int Power = 0;
			int fband = spBand.getSelectedItemPosition();
			int band = 0;
			if (fband == 0)
				band = 1;
			if (fband == 1)
				band = 2;
			if (fband == 2)
				band = 3;
			if (fband == 3)
				band = 4;
			if (fband == 4)
				band = 8;
			int Frequent = spminFrm.getSelectedItemPosition();
			MinFre = Frequent;
			Frequent = spmaxFrm.getSelectedItemPosition();
			MaxFre = Frequent;
			Power = tvpowerdBm.getSelectedItemPosition();
			int result = UHfGetData.SetUhfInfo((byte) band, (byte) MaxFre,
					(byte) MinFre, (byte) Power);
			//int result = UHfGetData.SetRegion((byte)band, (byte)MaxFre, (byte)MinFre);
			//int result = UHfGetData.SetRfPower((byte) Power);
			if (result == 0) {
				Toast.makeText(getApplicationContext(), getString(R.string.set_success),
						Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(getApplicationContext(), getString(R.string.set_fail),
						Toast.LENGTH_SHORT).show();
			}
		} else if (view == bRead) {
			UHfGetData.GetUhfInfo();
			mHandler.removeMessages(MSG_SHOW_PROPERTIES);
			mHandler.sendEmptyMessage(MSG_SHOW_PROPERTIES);
		}
	}

	private void showResult(String version, int band, int dminfre, int dmaxfre,
			int powerdbm, int scanTime, int BeepEn, int AntInfo) {
		try {
			tvVersion.setText(version.toUpperCase());
			SetFre(band);
			if (band == 8) {
				band = band - 4;
			} else {
				band = band - 1;
			}
			int frequent = ((dminfre & 0x3F) & 255);
			spminFrm.setSelection(frequent, true);
			spBand.setSelection(band, true);
			frequent = ((dmaxfre & 0x3F) & 255);
			spmaxFrm.setSelection(frequent, true);
			tvpowerdBm.setSelection(powerdbm, true);
			tvBeep.setSelection(BeepEn, true);
		} catch (Exception ex) {
		}
	}

	private void SetFre(int m) {
		if (m == 1) {
			strmaxFrm = new String[20];
			strminFrm = new String[20];
			for (int i = 0; i < 20; i++) {
				String temp = "";
				float values = (float) (920.125 + i * 0.25);
				temp = String.valueOf(values) + "MHz";
				strminFrm[i] = temp;
				strmaxFrm[i] = temp;
			}
			spmaxFrm = (Spinner) findViewById(R.id.max_spinner);
			spada_maxFrm = new ArrayAdapter<String>(ScanView.this,
					android.R.layout.simple_spinner_item, strmaxFrm);
			spada_maxFrm
					.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spmaxFrm.setAdapter(spada_maxFrm);
			spmaxFrm.setSelection(19, false);

			spminFrm = (Spinner) findViewById(R.id.min_spinner);
			spada_minFrm = new ArrayAdapter<String>(ScanView.this,
					android.R.layout.simple_spinner_item, strminFrm);
			spada_minFrm
					.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spminFrm.setAdapter(spada_minFrm);
			spminFrm.setSelection(0, false);
		} else if (m == 2) {
			strmaxFrm = new String[50];
			strminFrm = new String[50];
			for (int i = 0; i < 50; i++) {
				String temp = "";
				float values = (float) (902.75 + i * 0.5);
				temp = String.valueOf(values) + "MHz";
				strminFrm[i] = temp;
				strmaxFrm[i] = temp;
			}
			spmaxFrm = (Spinner) findViewById(R.id.max_spinner);
			spada_maxFrm = new ArrayAdapter<String>(ScanView.this,
					android.R.layout.simple_spinner_item, strmaxFrm);
			spada_maxFrm
					.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spmaxFrm.setAdapter(spada_maxFrm);
			spmaxFrm.setSelection(49, false);

			spminFrm = (Spinner) findViewById(R.id.min_spinner);
			spada_minFrm = new ArrayAdapter<String>(ScanView.this,
					android.R.layout.simple_spinner_item, strminFrm);
			spada_minFrm
					.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spminFrm.setAdapter(spada_minFrm);
			spminFrm.setSelection(0, false);
		} else if (m == 3) {
			strmaxFrm = new String[32];
			strminFrm = new String[32];
			for (int i = 0; i < 32; i++) {
				String temp = "";
				float values = (float) (917.1 + i * 0.2);
				temp = String.valueOf(values) + "MHz";
				strminFrm[i] = temp;
				strmaxFrm[i] = temp;
			}
			spmaxFrm = (Spinner) findViewById(R.id.max_spinner);
			spada_maxFrm = new ArrayAdapter<String>(ScanView.this,
					android.R.layout.simple_spinner_item, strmaxFrm);
			spada_maxFrm
					.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spmaxFrm.setAdapter(spada_maxFrm);
			spmaxFrm.setSelection(31, false);

			spminFrm = (Spinner) findViewById(R.id.min_spinner);
			spada_minFrm = new ArrayAdapter<String>(ScanView.this,
					android.R.layout.simple_spinner_item, strminFrm);
			spada_minFrm
					.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spminFrm.setAdapter(spada_minFrm);
			spminFrm.setSelection(0, false);
		} else if (m == 4) {
			strmaxFrm = new String[15];
			strminFrm = new String[15];
			for (int i = 0; i < 15; i++) {
				String temp = "";
				float values = (float) (865.1 + i * 0.2);
				temp = String.valueOf(values) + "MHz";
				strminFrm[i] = temp;
				strmaxFrm[i] = temp;
			}
			spmaxFrm = (Spinner) findViewById(R.id.max_spinner);
			spada_maxFrm = new ArrayAdapter<String>(ScanView.this,
					android.R.layout.simple_spinner_item, strmaxFrm);
			spada_maxFrm
					.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spmaxFrm.setAdapter(spada_maxFrm);
			spmaxFrm.setSelection(14, false);

			spminFrm = (Spinner) findViewById(R.id.min_spinner);
			spada_minFrm = new ArrayAdapter<String>(ScanView.this,
					android.R.layout.simple_spinner_item, strminFrm);
			spada_minFrm
					.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spminFrm.setAdapter(spada_minFrm);
			spminFrm.setSelection(0, false);
		} else if (m == 8) {
			strmaxFrm = new String[20];
			strminFrm = new String[20];
			for (int i = 0; i < 20; i++) {
				String temp = "";
				float values = (float) (840.125 + i * 0.25);
				temp = String.valueOf(values) + "MHz";
				strminFrm[i] = temp;
				strmaxFrm[i] = temp;
			}
			spmaxFrm = (Spinner) findViewById(R.id.max_spinner);
			spada_maxFrm = new ArrayAdapter<String>(ScanView.this,
					android.R.layout.simple_spinner_item, strmaxFrm);
			spada_maxFrm
					.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spmaxFrm.setAdapter(spada_maxFrm);
			spmaxFrm.setSelection(19, false);

			spminFrm = (Spinner) findViewById(R.id.min_spinner);
			spada_minFrm = new ArrayAdapter<String>(ScanView.this,
					android.R.layout.simple_spinner_item, strminFrm);
			spada_minFrm
					.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spminFrm.setAdapter(spada_minFrm);
			spminFrm.setSelection(0, false);
		}
	}

	private String getVersion(byte[] b) {
		if (b != null && b.length == 2)
			return b[0] + "." + b[1];
		return "";
	}

	private String getStr(byte[] b) {
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < b.length; i++) {
			sb.append(b[i]);
		}
		return sb.toString();
	}

}
