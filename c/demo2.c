int main(void) {
	int arr[3]={1,2,3};
	int a = arr[0];
	int b = arr[1];
	int c = arr[2];
	int *p = &arr[0];
	int e = *p;
	int f = *(p+1);
	int g = *(p+2);
	return 1;
}
